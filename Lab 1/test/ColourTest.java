import org.junit.Test;
import main.Colour;
import static org.junit.Assert.assertEquals;

public class ColourTest {

    @Test
    public void testDecode() {
        Colour decodeTest = Colour.decode("0x1FF0FF");
        int rt = decodeTest.getR();
        int gt = decodeTest.getG();
        int bt = decodeTest.getB();
        assertEquals(31, rt);
        assertEquals(240, gt);
        assertEquals(255, bt);
    }

    @Test
    public void testRGBtoCMYK() {
        float[] RGBtoCMYKtest = Colour.RGBtoCMYK(31, 240, 255, new float[4]);
        assertEquals(0.8784313797950745, RGBtoCMYKtest[0], 1e-6);
        assertEquals(0.05882352590560913, RGBtoCMYKtest[1], 1e-6);
        assertEquals(0.0, RGBtoCMYKtest[2], 1e-6);
        assertEquals(0.0, RGBtoCMYKtest[3], 1e-6);
    }

    @Test
    public void testRGBtoHSB() {
        float[] RGBtoHSBtest = Colour.RGBtoHSB(31, 240, 255, new float[3]);
        assertEquals(0.5111607313156128, RGBtoHSBtest[0], 1e-6);
        assertEquals(0.8784313797950745, RGBtoHSBtest[1], 1e-6);
        assertEquals(1.0, RGBtoHSBtest[2], 1e-6);
    }

    @Test
    public void testRGBtoHSL() {
        float[] RGBtoHSLtest = Colour.RGBtoHSL(31, 240, 255, new float[3]);
        assertEquals(0.5111607313156128, RGBtoHSLtest[0], 1e-6);
        assertEquals(1.0, RGBtoHSLtest[1], 1e-6);
        assertEquals(0.5607843399047852, RGBtoHSLtest[2], 1e-6);
    }
}