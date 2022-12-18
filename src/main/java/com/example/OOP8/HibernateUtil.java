package com.example.OOP8;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static Session session;
    static {
        Configuration cfg = new Configuration().configure(new File("C:\\Users\\MISHA\\IdeaProjects\\OOP8\\src\\main\\resources\\hibernate.cfg.xml"));
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        sessionFactory = cfg.buildSessionFactory();
    }
    public static Session configureSession() {
        session = sessionFactory.openSession();
        return session;
    }

}
