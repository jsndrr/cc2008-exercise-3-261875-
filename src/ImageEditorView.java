import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorView extends JFrame {
    JPanel mainPanel = new JPanel();
    JButton loadImageButton = new JButton("Load Image");
    JButton negativeFilterButton = new JButton("Negative");
    JButton grayscaleFilterButton = new JButton("Grayscale");
    JButton undoButton = new JButton("<- Undo");
    JFileChooser inputImageChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpeg", "jpg");
    ImagePanel imagePanel;

    public ImageEditorView() {
        // We are extending the JFrame class, so we MUST call the parent constructor.
        super("Editor UVG");

        // orientation of main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // methods on the parent JFrame class
        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputImageChooser.setFileFilter(filter);

        mainPanel.add(loadImageButton);

        // Filter Buttons
        // negative, grayscale
        mainPanel.add(negativeFilterButton);
        mainPanel.add(grayscaleFilterButton);
        mainPanel.add(undoButton);

        // at last, add the main panel to the JFrame
        add(mainPanel);
    }

    // ################## A section to register action listeners ################
    public void addLoadImageListener(ActionListener listener) {
        loadImageButton.addActionListener(listener);
    }

    public void addNegativeListener(ActionListener listener) {
        negativeFilterButton.addActionListener(listener);
    }

    public void addGrayscaleListener(ActionListener listener) {
        grayscaleFilterButton.addActionListener(listener);
    }

    public void addInputImageChooserListener(ActionListener listener) {
        inputImageChooser.addActionListener(listener);
    }

    public void addUndoListener(ActionListener listener) {
        undoButton.addActionListener(listener);
    }

    // ############### A section to trigger actions in the GUI ##################
    public File showInputImageChooser() {
        int returnVal = inputImageChooser.showOpenDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        return inputImageChooser.getSelectedFile();
    }

    public void showInputImage(BufferedImage image) {
        if (imagePanel != null) {
            mainPanel.remove(imagePanel);
        }

        imagePanel = new ImagePanel(image);
        imagePanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(imagePanel);
        pack();
    }

    public void showMessageDialog(String msg) {
        JOptionPane.showMessageDialog(
                this,
                msg,
                "Info",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void showInfoDialogue(String msg) {
        JOptionPane.showMessageDialog(
                this,
                msg,
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorDialogue(String msg) {
        JOptionPane.showMessageDialog(
                this,
                msg,
                "ERROR!",
                JOptionPane.ERROR_MESSAGE);
    }
}
