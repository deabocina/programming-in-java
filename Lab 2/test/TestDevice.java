import static org.junit.Assert.assertNotNull;

import org.testng.annotations.Test;
import main.WaterMeasure;

public class TestDevice {
    @Test
    void deviceTest() {
        WaterMeasure value = new WaterMeasure("tcp://mqtt.eclipseprojects.io:1883", "iMeasure");
        assertNotNull(value);
    }
}