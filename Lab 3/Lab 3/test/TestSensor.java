import static org.junit.Assert.assertNotNull;
import org.testng.annotations.Test;
import main.Sensor;

public class TestSensor {
    @Test
    void sensorTest() {
        Sensor s = new Sensor("Temperature");
        assertNotNull(s);
    }
}