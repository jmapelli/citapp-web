package pe.lizard.citapp.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Connection {

    public static final String PERSISTENCE_UNIT_NAME = "pe.lizard.citapp.db.pu";
    private static EntityManager em;

    private Connection() {
    }

    public static EntityManager getInstance() {
        return em;
    }

    public static void init() {
        em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME).createEntityManager();
    }

    public static void close() {
        em.close();
    }

}
