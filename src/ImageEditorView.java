import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorView extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel controlsPanel = new JPanel();
    private JButton loadImageButton = new JButton("Cargar Imagen");
    private JButton negativeFilterButton = new JButton("Negativo");
    private JButton grayscaleFilterButton = new JButton("Grises");
    private JButton undoButton = new JButton("<- Deshacer");
    private JFileChooser inputImageChooser = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "png", "jpeg", "jpg");
    private ImagePanel imagePanel;

    public ImageEditorView() {
        super("Editor de Filtros - UVG");
        setLayout(new BorderLayout());

        inputImageChooser.setFileFilter(filter);

        controlsPanel.setLayout(new FlowLayout());
        controlsPanel.add(loadImageButton);
        controlsPanel.add(negativeFilterButton);
        controlsPanel.add(grayscaleFilterButton);
        controlsPanel.add(undoButton);

        add(controlsPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addLoadImageListener(ActionListener listener) { loadImageButton.addActionListener(listener); }
    public void addNegativeListener(ActionListener listener) { negativeFilterButton.addActionListener(listener); }
    public void addGrayscaleListener(ActionListener listener) { grayscaleFilterButton.addActionListener(listener); }
    public void addUndoListener(ActionListener listener) { undoButton.addActionListener(listener); }

    public File showInputImageChooser() {
        int returnVal = inputImageChooser.showOpenDialog(this);
        return (returnVal == JFileChooser.APPROVE_OPTION) ? inputImageChooser.getSelectedFile() : null;
    }

    public void showInputImage(BufferedImage image) {
        if (imagePanel != null) {
            mainPanel.remove(imagePanel);
        }
        imagePanel = new ImagePanel(image);
        imagePanel.setPreferredSize(new Dimension(700, 450));
        mainPanel.add(imagePanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showErrorDialogue(String msg) {
        JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}