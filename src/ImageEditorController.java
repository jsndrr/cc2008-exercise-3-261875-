import java.io.File;

public class ImageEditorController {
    private ImageEditorView view;
    private ImageEditorModel model;

    public ImageEditorController(ImageEditorModel model, ImageEditorView view) {
        this.view = view;
        this.model = model;

        // hookup action listeners
        this.view.addLoadImageListener(e -> handleLoadImage());
        this.view.addNegativeListener(e -> handleNegativeFilter());
        this.view.addGrayscaleListener(e -> handleGrayscaleFilter());
        this.view.addUndoListener(e -> handleUndoButton());
    }

    public void handleLoadImage() {
        File selectedFile = view.showInputImageChooser();
        if (selectedFile == null) {
            return;
        }

        try {
            // mutate the application state
            model.setInputFileName(selectedFile.getAbsolutePath());
            model.setInputImage(ImageUtils.load(selectedFile.getAbsolutePath()));
        } catch (Exception e) {
            // view.showErrorDialog("couldn't load image: " + e.getMessage());
        }

        // we updated the state of the model, we must re-draw the view layer
        refresh();
    }

    // What do we want to do when someone presses the
    // negative filter button?
    private void handleNegativeFilter() {
        try {
            Image negative = this.model.negativeFilter();

            // application state changed, the view MUST be
            // updated
            this.view.showInputImage(ImageUtils.toBufferedImage(negative));
        } catch (ImageNotFoundException e) {
            // mostrar un error al usuario
            this.view.showInfoDialogue(e.getMessage());
        } catch (Exception e) {
            // mostrar el error al usuario
        }
    }

    private void handleGrayscaleFilter() {
        try {
            Image negative = this.model.grayscaleFilter();

            // application state changed, the view MUST be
            // updated
            this.view.showInputImage(ImageUtils.toBufferedImage(negative));
        } catch (ImageNotFoundException e) {
            // mostrar un error al usuario
            this.view.showInfoDialogue(e.getMessage());
        } catch (Exception e) {
            // mostrar el error al usuario
        }
    }

    private void handleUndoButton() {
        Image img = this.model.undo();
        view.showInputImage(ImageUtils.toBufferedImage(img));
    }

    // call the view to re-draw the application state
    private void refresh() {
        view.showInputImage(ImageUtils.toBufferedImage(model.getInputImage()));
    }
}
