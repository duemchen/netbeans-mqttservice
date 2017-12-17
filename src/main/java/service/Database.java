/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.Kunde;
import db.Position;
import db.Spiegel;
import db.Ziel;
import de.horatio.common.HoraFile;
import de.horatio.common.HoraTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.json.JSONObject;

/**
 *
 * @author duemchen
 */
public class Database {

    public void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("xyz");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    void fillExamples() {
        Database start = new Database();
        Kunde kunde = new Kunde();
        kunde.setName("Wolfgang Dümchen");
        kunde.setKennung("123abc");
        kunde.setLatitude(0);
        kunde.setLongitude(0);
        kunde.setOrt("Rheinsberg");
        start.persist(kunde);
        //
        Spiegel spiegel = new Spiegel();
        spiegel.setKunde(kunde);
        spiegel.setName("Ost");
        spiegel.setMac("80-1F-02-ED-FD-A6");
        spiegel.setRuhe(0);
        spiegel.setSonnenhoehe(10);
        spiegel.setSonnenwinkelMorgens(0);
        spiegel.setSonnenwinkelAbends(0);
        spiegel.setWindmax(5.1);
        spiegel.setWolkenmax(74);
        spiegel.setWind(180);
        start.persist(spiegel);
        //
        Ziel ziel;
        //
        ziel = new Ziel();
        ziel.setSpiegel(spiegel);
        ziel.setName("Wärmekollektor");
        start.persist(ziel);
        ziel = new Ziel();
        ziel.setSpiegel(spiegel);
        ziel.setName("Küche");
        start.persist(ziel);
        //
        spiegel = new Spiegel();
        spiegel.setKunde(kunde);
        spiegel.setName("West");
        spiegel.setMac("74-DA-38-3E-E8-3C");
        spiegel.setRuhe(0);
        spiegel.setSonnenhoehe(10);
        spiegel.setSonnenwinkelMorgens(0);
        spiegel.setSonnenwinkelAbends(0);
        spiegel.setWindmax(5.1);
        spiegel.setWolkenmax(74);
        spiegel.setWind(180);
        start.persist(spiegel);
        //
        ziel = new Ziel();
        ziel.setSpiegel(spiegel);
        ziel.setName("Wärmekollektor");
        start.persist(ziel);
        ziel = new Ziel();
        ziel.setSpiegel(spiegel);
        ziel.setName("Küche");
        start.persist(ziel);
        //
        Position pos = new Position();
        pos.setZiel(ziel);
        pos.setDatum(new Date());
        pos.setData("ein json String");
        //.deflate() ggf json inhalt gleich in die felder umfüllen, um dann schneller zu öffnen
        //start.persist(pos);
    }

    void fileToPositions(String filename) {

        ArrayList<String> list = new ArrayList<String>();
        HoraFile.FillDateiToArrayList(filename, list, "UTF-8");
        String s;
        for (String zeile : list) {
            try {

                System.out.println(zeile);
                JSONObject jo = new JSONObject(zeile);
                System.out.println("jo: " + jo + "\n\n");
                Position pos = new Position();
                s = jo.getString("time");
                Date datum = HoraTime.strToDateTime(s);
                pos.setDatum(datum);
                s = jo.getString("topic");
                int i = s.lastIndexOf("/");
                i++;
                s = s.substring(i);
                Ziel ziel = macToZiel(s);
                pos.setZiel(ziel);
                int x = jo.getInt("dir");
                pos.setX(x);
                int y = jo.getInt("pitch");
                pos.setY(y);
                int z = jo.getInt("roll");
                pos.setZ(z);
                pos.setData(zeile);
                persist(pos);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    private Ziel macToZiel(String s) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("xyz");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select s from Spiegel as s where s.mac=:MAC");
        q.setParameter("MAC", s);
        List<Spiegel> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        Spiegel sp = list.get(0);
        System.out.println("sp: " + sp);
        // ein oder das Ziel dieses Spiegels
        q = em.createQuery("select z from Ziel as z where z.spiegel=:SPIEGEL");
        q.setParameter("SPIEGEL", sp);
        List<Ziel> listz = q.getResultList();
        if (listz.isEmpty()) {
            return null;
        }
        Ziel ziel = listz.get(0);
        System.out.println("ziel " + ziel);
        return ziel;
    }

}
