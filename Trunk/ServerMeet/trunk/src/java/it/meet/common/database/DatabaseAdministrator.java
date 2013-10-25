package it.meet.common.database;

import it.meet.service.common.util.ErrorCodeEnumeration;
import it.meet.service.common.util.MeetException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Class used to manage the database.
 *
 * @author Luigi Vorraro
 */
public class DatabaseAdministrator {

    /**
     * The singleton instance
     */
    private static DatabaseAdministrator instance;

    /**
     * The session factory used to create a new session
     */
    private SessionFactory sessionFactory;

    /**
     * Return the singleton instance
     *
     * @return
     */
    public static DatabaseAdministrator getInstance() throws MeetException {
        if (instance == null) {
            instance = new DatabaseAdministrator();
        }
        return instance;
    }

    /**
     * Cretate the object
     */
    private DatabaseAdministrator() throws MeetException {
        try {
             sessionFactory = HibernateUtil.getSessionFactory();
        } catch (Exception ex) {
            throw new MeetException(ErrorCodeEnumeration.MEET9999, ex);
        }
    }
    
    /**
     * Apre una nuova sessione di lavoro sul database configurato
     *
     * @author alessandro guida
     * @return
     */
    public Session openSession() {
        return sessionFactory.openSession();
    }
    
}
