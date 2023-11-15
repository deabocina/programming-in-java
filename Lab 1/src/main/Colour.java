package main;

public class Colour {
    private int r, g, b;

    public Colour(int r, int g, int b) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Colour decode(String hexColor) {
        int red = Integer.valueOf(hexColor.substring(2, 4), 16);
        int green = Integer.valueOf(hexColor.substring(4, 6), 16);
        int blue = Integer.valueOf(hexColor.substring(6, 8), 16);

        Colour col = new Colour(red, green, blue);
        return col;
    }

    public void setR(int r) {
        this.r = r;
    }
    public int getR() {
        return r;
    }

    public void setG(int g) {
        this.g = g;
    }
    public int getG() {
        return g;
    }

    public void setB(int b) {
        this.b = b;
    }
    public int getB() {
        return b;
    }

    public int getRGB() {
        return (256 * 256 * r + 256 * g + b);
    }

    public static float[] RGBtoHSB(int r2, int g2, int b2, float[] hsbCode) {
        float r1 = r2 / 255f;
        float g1 = g2 / 255f;
        float b1 = b2 / 255f;

        float rgbmax = Math.max(r1, Math.max(g1, b1));
        float rgbmin = Math.min(r1, Math.min(g1, b1));
        float rgbdiff = (rgbmax - rgbmin);

        float h = -1, s = -1;
        if (rgbmax == rgbmin) {
            h = 0f;
        }
        else if (rgbmax == r1) {
            h = (60 * ((g1 - b1) / rgbdiff) + 360) / 360f;
        }
        else if (rgbmax == g1) {
            h = (60 * ((b1 - r1) / rgbdiff) + 120) / 360f;
        }
        else if (rgbmax == b1) {
            h = (60 * ((r1 - g1) / rgbdiff) + 240) / 360f;
        }

        if (rgbmax == 0)
            s = 0f;
        else
            s = rgbdiff / rgbmax;

        float b = rgbmax;

        hsbCode[0] = h;
        hsbCode[1] = s;
        hsbCode[2] = b;

        return hsbCode;
    }

    public static float[] RGBtoHSL(int r2, int g2, int b2, float[] hslCode) {
        float r1 = r2 / 255f;
        float g1 = g2 / 255f;
        float b1 = b2 / 255f;

        float rgbmax = Math.max(r1, Math.max(g1, b1));
        float rgbmin = Math.min(r1, Math.min(g1, b1));
        float rgbdiff = (rgbmax - rgbmin);

        float h = -1, s = -1, l = -1;
        l = (rgbmax + rgbmin) / 2f;
        if (rgbmax == rgbmin) {
            h = 0f;
        }
        else if (rgbmax == r1) {
            h = (60 * ((g1 - b1) / rgbdiff) + 360) / 360;
        }
        else if (rgbmax == g1) {
            h = (60 * (2 + (b1 - r1) / rgbdiff)) / 360;
        }
        else if (rgbmax == b1) {
            h = (60 * (4 + (r1 - g1) / rgbdiff)) / 360;
        }

        if (l <= 0.5)
            s = (rgbmax - rgbmin) / (rgbmax + rgbmin);
        else
            s = (rgbmax - rgbmin) / (2 - rgbmax - rgbmin);

        hslCode[0] = h;
        hslCode[1] = s;
        hslCode[2] = l;

        return hslCode;
    }

    public static float[] RGBtoCMYK(int r2, int g2, int b2, float[] cmykCode) {
        float r1 = r2 / 255f;
        float g1 = g2 / 255f;
        float b1 = b2 / 255f;
        float Black = 1.0f - Math.max(r1, Math.max(g1, b1));

        if (r1 == 0 && g1 == 0 && b1 == 0) {
            Black = 1;
        }
        float[] cmykC = { 0, 0, 0, 1 };
        if (Black == 1) {
            cmykCode = cmykC;
            return cmykCode;
        }

        else {
            float Cyan = (1f - r1 - Black) / (1f - Black);
            float Magenta = (1f - g1 - Black) / (1f - Black);
            float Yellow = (1f - b1 - Black) / (1f - Black);

            cmykCode[0] = Cyan;
            cmykCode[1] = Magenta;
            cmykCode[2] = Yellow;
            cmykCode[3] = Black;

            return cmykCode;
        }
    }
}