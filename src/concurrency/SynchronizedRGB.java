package concurrency;

public class SynchronizedRGB {
    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red, int green, int blue, String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 -red;
        green = 255 -green;
        blue = 255 -blue;
        name = "Inverse of " + name;
    }

    public static void main(String[] args) {
        SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");
        // 如果不加synchronized代码块
        // 在getRGB之后getName之前，有线程修改了这个对象
        // int和Name可能不一致
        synchronized (color) {
            int myColorInt = color.getRGB();
            String myColorName = color.getName();
        }
    }
}
