package pl.wp.gameofthroneapplication.databaseutils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomUserSessionFactory {

    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure("static/hibernate.cfg.xml");
        return cfg.buildSessionFactory();
    }
}