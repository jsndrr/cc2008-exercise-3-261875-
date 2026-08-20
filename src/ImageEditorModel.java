public class ImageEditorModel {
    private String inputFileName;
    private Image inputImage;
    private ImageEditor editor;

    public String getInputFileName() {
        return this.inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Image getInputImage() {
        return this.inputImage;
    }

    public void setInputImage(Image inputImage) {
        this.inputImage = inputImage;
        this.editor = new ImageEditor(this.inputImage);
    }

    public Image negativeFilter() {
        return this.editor.negative();
    }
}
