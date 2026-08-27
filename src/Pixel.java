public class Pixel {
    private int r;
    private int g;
    private int b;

    public Pixel(int r, int g, int b) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
    }

    public int getR() { return r; }
    public void setR(int r) { this.r = clamp(r); }

    public int getG() { return g; }
    public void setG(int g) { this.g = clamp(g); }

    public int getB() { return b; }
    public void setB(int b) { this.b = clamp(b); }

    public int getAverage() {
        return (r + g + b) / 3;
    }

    public Pixel copy() {
        return new Pixel(this.r, this.g, this.b);
    }

    private static int clamp(int value) {
        if (value < 0) return 0;
        if (value > 255) return 255;
        return value;
    }
}