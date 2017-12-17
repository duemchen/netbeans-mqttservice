/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author duemchen
 */
public class MqttConnector {

    private String MQTTLINK = "duemchen.feste-ip.net:56686";
    private MqttClient client;

    public synchronized void sendMqtt(String path, String data) {
        try {
            JSONObject jo = new JSONObject();
            jo.put("source", 0); // der Regler sendet das Kommando selbst

            try {
                if (client == null) {
                    client = new MqttClient("tcp://" + MQTTLINK, "joyit");
                }
                if (!client.isConnected()) {
                    client.connect();

                }
                MqttMessage message = new MqttMessage();
                //message.setPayload(jo.toString().getBytes());
                message.setPayload(data.getBytes());
                message.setRetained(true);
                //client.publish("simago/joy", message);
                client.publish(path, message);
                // log.info("sendCommand " + path + ":" + cmd);
                System.out.println("sendCommand:: " + path + ":" + data + "\n");

            } catch (MqttException ex) {
                System.out.println(ex);
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JSONException ex) {
        }

    }

}
