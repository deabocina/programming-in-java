package main;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class WaterMeasure {
    private static final Logger logger = Logger.getLogger(WaterMeasure.class.getName());
    private List<Sensor> sensorList;
    private final String broker;
    private final String topic;
    private final MemoryPersistence persistence = new MemoryPersistence();

    public WaterMeasure(String broker, String topic) {
        this.broker = broker;
        this.topic = topic;
    }

    public static void main(String[] args) {
        WaterMeasure connect = new WaterMeasure("tcp://localhost:1883", "iMeasure");

        try {
            connect.loadSensorConfigurations("src/resources/config.json");
            connect.runWater();
        } catch (MqttException | IOException e) {
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    public void loadSensorConfigurations(String configFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(configFile);
        sensorList = objectMapper.readValue(file, new TypeReference<>() {});
    }

    public void runWater() throws MqttException {
        final String clientId = "WaterClient";

        MqttClient client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);

        client.connect(options);

        if (sensorList == null || sensorList.isEmpty()) {
            logger.log(Level.WARNING, "Sensor list is empty or null.");
            return;
        }

        for (Sensor sensor : sensorList) {
            String message = sensor.feedback();
            MqttMessage converted = new MqttMessage(message.getBytes());

            client.publish(topic, converted);
            System.out.println(sensor.feedback());
        }
        client.disconnect();
    }
}
