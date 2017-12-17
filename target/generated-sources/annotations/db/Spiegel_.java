package db;

import db.Kunde;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-17T17:39:10")
@StaticMetamodel(Spiegel.class)
public class Spiegel_ { 

    public static volatile SingularAttribute<Spiegel, Double> sonnenhoehe;
    public static volatile SingularAttribute<Spiegel, Double> sonnenwinkelAbends;
    public static volatile SingularAttribute<Spiegel, Double> wolkenmax;
    public static volatile SingularAttribute<Spiegel, String> mac;
    public static volatile SingularAttribute<Spiegel, Double> wolke;
    public static volatile SingularAttribute<Spiegel, Double> windmax;
    public static volatile SingularAttribute<Spiegel, Double> ruhe;
    public static volatile SingularAttribute<Spiegel, Double> sonnenwinkelMorgens;
    public static volatile SingularAttribute<Spiegel, String> name;
    public static volatile SingularAttribute<Spiegel, Long> id;
    public static volatile SingularAttribute<Spiegel, String> camera;
    public static volatile SingularAttribute<Spiegel, Kunde> kunde;
    public static volatile SingularAttribute<Spiegel, Double> wind;

}