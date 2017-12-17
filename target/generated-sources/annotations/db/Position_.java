package db;

import db.Ziel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-17T17:39:10")
@StaticMetamodel(Position.class)
public class Position_ { 

    public static volatile SingularAttribute<Position, Date> datum;
    public static volatile SingularAttribute<Position, Boolean> loesch;
    public static volatile SingularAttribute<Position, String> data;
    public static volatile SingularAttribute<Position, Long> x;
    public static volatile SingularAttribute<Position, Long> y;
    public static volatile SingularAttribute<Position, Long> z;
    public static volatile SingularAttribute<Position, Long> id;
    public static volatile SingularAttribute<Position, Ziel> ziel;

}