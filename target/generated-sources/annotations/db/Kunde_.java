package db;

import db.Spiegel;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-17T17:39:10")
@StaticMetamodel(Kunde.class)
public class Kunde_ { 

    public static volatile SingularAttribute<Kunde, String> ort;
    public static volatile SingularAttribute<Kunde, String> kennung;
    public static volatile ListAttribute<Kunde, Spiegel> spiegel;
    public static volatile SingularAttribute<Kunde, Double> latitude;
    public static volatile SingularAttribute<Kunde, String> name;
    public static volatile SingularAttribute<Kunde, Long> id;
    public static volatile SingularAttribute<Kunde, Double> longitude;

}