package main;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;

public class WaterMeasure {
    private static final Logger logger = Logger.getLogger(WaterMeasure.class.getName());
    List<Sensor> sensorList = new ArrayList<>();
    String broker, topic, clientId = "WaterClient";
    MemoryPersistence persistance = new MemoryPersistence();

    public WaterMeasure(String broker, String topic) {
        this.broker = broker;
        this.topic = topic;
    }

    void runiWater() throws MqttException {
        MqttClient client = new MqttClient(this.broker, this.clientId, this.persistance);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);

        try {
            client.connect(options);
        } catch (MqttException e) {
            logger.log(Level.SEVERE, "An MQTT Exception occurred", e);
        }

        for (int i = 0; i < 10; i++) {
            sensorList.add(new Sensor("Temperature"));
        }

        for (Sensor sensor : sensorList) {
            String message = sensor.feedback();
            MqttMessage converted = new MqttMessage(message.getBytes());

            try {
                client.publish(this.topic, converted);
            } catch (MqttException e) {
                logger.log(Level.SEVERE, "An MQTT Exception occurred", e);
            }

            System.out.println(sensor.feedback());
        }
        client.disconnect();
    }
}