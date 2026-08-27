public class ImageEditor {
    private Image og;

    public ImageEditor(Image og) {
        this.og = og;
    }

    public Image negative() {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                int r = 255 - p.getR();
                int g = 255 - p.getG();
                int b = 255 - p.getB();
                transformed.setPixel(row, col, new Pixel(r, g, b));
            }
        }
        return transformed;
    }

    public Image grayscale() {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                int avg = p.getAverage();
                transformed.setPixel(row, col, new Pixel(avg, avg, avg));
            }
        }
        return transformed;
    }

    public Image keepOnlyChannel(int channel) {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                int r = (channel == 0) ? p.getR() : 0;
                int g = (channel == 1) ? p.getG() : 0;
                int b = (channel == 2) ? p.getB() : 0;
                transformed.setPixel(row, col, new Pixel(r, g, b));
            }
        }
        return transformed;
    }

    public Image brightness(int amount) {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                transformed.setPixel(row, col, new Pixel(p.getR() + amount, p.getG() + amount, p.getB() + amount));
            }
        }
        return transformed;
    }

    public Image blackAndWhite(int limit) {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                Pixel p = og.getPixel(row, col);
                int val = (p.getAverage() > limit) ? 255 : 0;
                transformed.setPixel(row, col, new Pixel(val, val, val));
            }
        }
        return transformed;
    }

    public Image mirrorHorizontal() {
        Image transformed = new Image(og.getHeight(), og.getWidth());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                transformed.setPixel(row, col, og.getPixel(row, og.getWidth() - 1 - col).copy());
            }
        }
        return transformed;
    }

    public Image rotate90() {
        Image transformed = new Image(og.getWidth(), og.getHeight());
        for (int row = 0; row < og.getHeight(); row++) {
            for (int col = 0; col < og.getWidth(); col++) {
                transformed.setPixel(col, og.getHeight() - 1 - row, og.getPixel(row, col).copy());
            }
        }
        return transformed;
    }
}