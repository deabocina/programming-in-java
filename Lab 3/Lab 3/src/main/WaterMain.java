package main;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.*;

public class WaterMain {
    private static final Logger logger = Logger.getLogger(WaterMain.class.getName()); // Define the logger

    public static void main(String[] args) throws IOException {
        WaterMeasure connect = new WaterMeasure("tcp://localhost:1883", "iMeasure");

        List<Sensor> sensors = Sensor.readFromJSON();
        for(Sensor s : sensors){
            System.out.println(s.feedback());
        }
        Sensor.writeToJSON(sensors);
    }
}