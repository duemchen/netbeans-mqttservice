/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author duemchen
 *
 * Datenbank füllen Messwerte einfüllen mqtt telegramme persist verschicken zu
 * den Status regelmässig aktualisieren(wetter) Als voraussetzung für webseite
 *
 * Der Regler selbst kann auch mit mqtt oder mit db arbeiten.
 *
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("Start...");
        Database db = new Database();
        MqttConnector mq = new MqttConnector();
        mq.subscibe("simago/system");
        mq.sendMqtt("simago/system", "Hallo Welt " + new Date());
        //

        db.fillExamples();
        db.fileToPositions("74-DA-38-3E-E8-3C.txt");
        db.fileToPositions("80-1F-02-ED-FD-A6.txt");
        //
        String mac = "80-1F-02-ED-FD-A6";
        JSONObject data = db.getPositions(mac);
        mq.sendMqttPersist("simago/position/" + mac, data.toString());
        //
        System.out.println("DB erzeugt.\n\n");
    }

}
