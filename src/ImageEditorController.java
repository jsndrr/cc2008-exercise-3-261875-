import java.io.File;

public class ImageEditorController {
    private ImageEditorView view;
    private ImageEditorModel model;

    public ImageEditorController(ImageEditorModel model, ImageEditorView view) {
        this.view = view;
        this.model = model;

        this.view.addLoadImageListener(e -> handleLoadImage());
        this.view.addNegativeListener(e -> handleNegativeFilter());
        this.view.addGrayscaleListener(e -> handleGrayscaleFilter());
        this.view.addUndoListener(e -> handleUndoButton());
    }

    private void handleLoadImage() {
        File selectedFile = view.showInputImageChooser();
        if (selectedFile == null) return;

        try {
            model.setInputFileName(selectedFile.getAbsolutePath());
            model.setInputImage(ImageUtils.load(selectedFile.getAbsolutePath()));
            refresh();
        } catch (Exception e) {
            view.showErrorDialogue("Error al cargar la imagen: " + e.getMessage());
        }
    }

    private void handleNegativeFilter() {
        try {
            Image negative = model.negativeFilter();
            view.showInputImage(ImageUtils.toBufferedImage(negative));
        } catch (ImageNotFoundException e) {
            view.showErrorDialogue(e.getMessage());
        }
    }

    private void handleGrayscaleFilter() {
        try {
            Image gray = model.grayscaleFilter();
            view.showInputImage(ImageUtils.toBufferedImage(gray));
        } catch (ImageNotFoundException e) {
            view.showErrorDialogue(e.getMessage());
        }
    }

    private void handleUndoButton() {
        try {
            Image previous = model.undo();
            view.showInputImage(ImageUtils.toBufferedImage(previous));
        } catch (EmptyHistoryException e) {
            view.showErrorDialogue(e.getMessage());
        }
    }

    private void refresh() {
        try {
            view.showInputImage(ImageUtils.toBufferedImage(model.getCurrentImage()));
        } catch (ImageNotFoundException ignored) {}
    }
}