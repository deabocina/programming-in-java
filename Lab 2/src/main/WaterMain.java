package main;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class WaterMain {
    private static final Logger logger = Logger.getLogger(WaterMain.class.getName()); // Define the logger

    public static void main(String[] args) {
        WaterMeasure connect = new WaterMeasure("tcp://mqtt.eclipseprojects.io:1883", "iMeasure");

        try {
            connect.runiWater();
        } catch (MqttException e) {
            // Log the exception with a specific logging level (e.g., SEVERE)
            logger.log(Level.SEVERE, "An MQTT Exception occurred", e);
        }
    }
}