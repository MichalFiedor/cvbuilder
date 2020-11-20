package pl.michalfiedor.cvbuilder.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.michalfiedor.cvbuilder.model.Cv;




public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addPackage("pl.michalfiedor.cvbuilder.model") //the fully qualified package name
                    .addAnnotatedClass(Cv.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
