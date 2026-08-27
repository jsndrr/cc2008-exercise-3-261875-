import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static Image load(String filename) throws IOException {
        File file = new File(filename);
        BufferedImage img = null;

        try {
            img = ImageIO.read(file);
            if (img == null) {
                throw new IOException("El archivo seleccionado no es una imagen valida.");
            }
        } finally {
            // Demostración del uso de finally: Notificación o limpieza técnica independiente del resultado
            System.out.println("Proceso de lectura finalizado para: " + filename);
        }

        int height = img.getHeight();
        int width = img.getWidth();
        Pixel[][] pixels = new Pixel[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int packed = img.getRGB(col, row);
                int r = (packed >> 16) & 0xFF;
                int g = (packed >> 8) & 0xFF;
                int b = packed & 0xFF;
                pixels[row][col] = new Pixel(r, g, b);
            }
        }

        return new Image(pixels);
    }

    public static void save(Image image, String filename) throws IOException {
        BufferedImage img = toBufferedImage(image);
        File file = new File(filename);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        String format = filename.endsWith(".jpg") || filename.endsWith(".jpeg") ? "jpg" : "png";
        ImageIO.write(img, format, file);
    }

    public static BufferedImage toBufferedImage(Image image) {
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel pixel = image.getPixel(row, col);
                int r = pixel.getR();
                int g = pixel.getG();
                int b = pixel.getB();
                output.setRGB(col, row, (r << 16) | (g << 8) | b);
            }
        }
        return output;
    }
}