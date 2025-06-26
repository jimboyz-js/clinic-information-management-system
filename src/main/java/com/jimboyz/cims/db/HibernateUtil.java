package com.jimboyz.cims.db;

import com.jimboyz.cims.err.ErrorDialog;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            sessionFactory = cfg.buildSessionFactory();
//            sessionFactory = new Configuration().configure().buildSessionFactory();
            cfg.setProperty("hibernate.search.backend.failure_handler", "fail"); //
            cfg.setProperty("hibernate.search.enabled", "false");


        } catch (HibernateException e) {
           ErrorDialog.show(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ErrorDialog.show(new HibernateException("SessionFactory is null. \nTry to turn on the server and restart the program."));
        }

        return sessionFactory;
    }
}
