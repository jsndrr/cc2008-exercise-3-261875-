import java.util.ArrayList;
import java.util.List;

public class ImageEditorModel {
    private String inputFileName;
    private ImageEditor editor;
    private List<Image> history;

    public ImageEditorModel() {
        this.history = new ArrayList<>();
    }

    public String getInputFileName() { return inputFileName; }
    public void setInputFileName(String inputFileName) { this.inputFileName = inputFileName; }

    public Image getCurrentImage() throws ImageNotFoundException {
        if (history.isEmpty()) {
            throw new ImageNotFoundException("No se ha cargado ninguna imagen.");
        }
        return history.get(history.size() - 1);
    }

    public void setInputImage(Image inputImage) {
        history.clear(); // Reiniciar historial al cargar una nueva imagen
        history.add(inputImage);
        this.editor = new ImageEditor(inputImage);
    }

    private void applyFilterResult(Image result) {
        history.add(result);
        this.editor = new ImageEditor(result);
    }

    public Image negativeFilter() throws ImageNotFoundException {
        getCurrentImage();
        Image result = editor.negative();
        applyFilterResult(result);
        return result;
    }

    public Image grayscaleFilter() throws ImageNotFoundException {
        getCurrentImage();
        Image result = editor.grayscale();
        applyFilterResult(result);
        return result;
    }

    public Image rotateFilter() throws ImageNotFoundException {
        getCurrentImage();
        Image result = editor.rotate90();
        applyFilterResult(result);
        return result;
    }

    public Image undo() throws EmptyHistoryException {
        if (history.size() <= 1) {
            throw new EmptyHistoryException("No hay mas operaciones previas para deshacer.");
        }
        history.remove(history.size() - 1);
        Image previous = history.get(history.size() - 1);
        this.editor = new ImageEditor(previous);
        return previous;
    }

    public int getHistorySize() {
        return history.size();
    }
}