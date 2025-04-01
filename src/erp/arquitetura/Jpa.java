package erp.arquitetura;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import erp.arquitetura.gui.Msg;

public class Jpa {

    private static final EntityManagerFactory emf;

    static {
	try {

	    emf = Persistence.createEntityManagerFactory("erp");

	} catch (Exception ex) {
	    Msg.erroConectarDataBase();
	    ex.printStackTrace();
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static Session getHibernateSession() {

	final SessionFactory sf = new Configuration().configure("persistence.xml").buildSessionFactory();
	// factory = new Configuration().configure().buildSessionFactory();
	final Session session = sf.openSession();
	return session;
    }

    public static EntityManagerFactory getEntityManagerFactory() {

	return emf;
    }
}