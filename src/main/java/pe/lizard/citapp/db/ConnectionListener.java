package pe.lizard.citapp.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener()
public class ConnectionListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ConnectionListener.class.getName());

    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("########## Inicializando conexión con base de datos ##########");
        Connection.init();
        LOG.info("########## Conexión establecida ##########");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("########## Cerrando conexión con base de datos ##########");
        Connection.close();
        LOG.info("########## Conexión cerrada ##########");
    }
}
