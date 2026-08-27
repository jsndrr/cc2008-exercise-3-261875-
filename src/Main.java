import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> {
            ImageEditorModel model = new ImageEditorModel();
            ImageEditorView view = new ImageEditorView();
            new ImageEditorController(model, view);
            view.setVisible(true);
        });
    }
}