package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure()
                    .addAnnotatedClass(ma.projet.classes.Employe.class)
                    .addAnnotatedClass(ma.projet.classes.Projet.class)
                    .addAnnotatedClass(ma.projet.classes.Tache.class)
                    .addAnnotatedClass(ma.projet.classes.EmployeTache.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Erreur de création SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
