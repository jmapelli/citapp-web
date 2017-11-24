package pe.lizard.citapp.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Connection {

    public static final String PERSISTENCE_UNIT_NAME_DEV = "pe.lizard.citapp.db.pu.dev";
    public static final String PERSISTENCE_UNIT_NAME_PROD = "pe.lizard.citapp.db.pu.prod";
    private static EntityManager em;

    private Connection() {
    }

    public static EntityManager getInstance() {
        return em;
    }

    public static void init() {
        String pu = System.getenv("citapp.prod");

        if (pu != null && pu.toLowerCase().equals("true")) {
            em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_PROD).createEntityManager();
        } else {
            em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_DEV).createEntityManager();
        }
    }

    public static void close() {
        em.close();
    }

}
