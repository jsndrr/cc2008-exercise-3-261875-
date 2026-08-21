import java.util.ArrayList;
import java.util.List;

public class ImageEditorModel {
    // The name of the input file that the user chose.
    private String inputFileName;
    // The ImageEditor holds all the logic for the image transformations.
    private ImageEditor editor;
    // image filtering history
    private List<Image> history;

    public ImageEditorModel() {
        this.history = new ArrayList<>();
    }

    public String getInputFileName() {
        return this.inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Image getInputImage() {
        return this.history.getFirst();
    }

    public void setInputImage(Image inputImage) {
        history.add(inputImage);
        this.editor = new ImageEditor(inputImage);
    }

    public Image negativeFilter() throws ImageNotFoundException {
        if (this.history.isEmpty()) {
            throw new ImageNotFoundException("image not found");
        }

        Image negative = this.editor.negative();
        history.add(negative);

        return history.getLast();
    }

    public Image grayscaleFilter() throws ImageNotFoundException {
        if (this.history.isEmpty()) {
            throw new ImageNotFoundException("image not found");
        }

        Image img = this.editor.grayscale();
        history.add(img);

        return history.getLast();
    }

    public Image undo() {
        history.removeLast();
        return history.getLast();
    }
}
