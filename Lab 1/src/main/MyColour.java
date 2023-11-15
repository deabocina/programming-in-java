package main;

public class MyColour {
    public static void main(String[] args) {

        String hexColor = "0x1FF0FF";

        Colour c = Colour.decode(hexColor);

        float[] hsbCode = new float[3];
        float[] hslCode = new float[3];
        float[] cmykCode = new float[4];

        Colour.RGBtoHSB(c.getR(), c.getG(), c.getB(), hsbCode);
        System.out.println("\nColour in HEX format: 0x" + Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Colour in RGB format: " + c.getR() + ", " + c.getG() + ", " + c.getB());
        System.out.println("Colour in HSB format: " + hsbCode[0] * 360 + "°, " + hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
        Colour.RGBtoHSL(c.getR(), c.getG(), c.getB(), hslCode);
        System.out.println("Colour in HSL format: " + hslCode[0] * 360 + "°,  " + hslCode[1] * 100 + "%, " + hslCode[2] * 100 + "%");
        Colour.RGBtoCMYK(c.getR(), c.getG(), c.getB(), cmykCode);
        System.out.println("Colour in CMYK format: " + cmykCode[0] + ", " + cmykCode[1] + ", " + cmykCode[2] + ", " + cmykCode[3]);

    }
}
