package pe.lizard.citapp.cita;

import pe.lizard.citapp.db.Connection;
import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioRepository;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class CitaRepository {

    private static final Logger LOG = Logger.getLogger(CitaRepository.class.getName());
    private EntityManager em;

    public CitaEntity crear(CitaEntity entity) {
        try {
            em = Connection.getInstance();

            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return entity;
    }
}
