package pe.lizard.citapp.cita;

import pe.lizard.citapp.db.Connection;
import pe.lizard.citapp.horario.HorarioEntity;
import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
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

    public List<CitaEntity> findByCodigo(String codigo, Long idDoctor) {
        List<CitaEntity> ces = null;

        try {
            em = Connection.getInstance();

            String sql = "SELECT c FROM CitaEntity c " +
                    "INNER JOIN c.doctor d " +
                    "WHERE d.id = :idDoctor and c.codigo like :codigo";

            Query q = em.createQuery(sql, CitaEntity.class);
            q.setParameter("idDoctor", idDoctor);
            q.setParameter("codigo", codigo);

            ces = (List<CitaEntity>) q.getResultList();
        } catch (Exception e) {
            LOG.warning(e.getMessage());
        }

        return ces;
    }

    public List<CitaEntity> findByNroDocumento(String nroDocumento, Long idDoctor) {
        List<CitaEntity> ces = null;

        try {
            em = Connection.getInstance();

            String sql = "SELECT c FROM CitaEntity c " +
                    "INNER JOIN c.doctor d " +
                    "INNER JOIN c.paciente p " +
                    "WHERE d.id = :idDoctor and p.nroDocumento  like :nroDocumento ";

            Query q = em.createQuery(sql, CitaEntity.class);
            q.setParameter("idDoctor", idDoctor);
            q.setParameter("nroDocumento", nroDocumento);

            ces = (List<CitaEntity>) q.getResultList();
        } catch (Exception e) {
            LOG.warning(e.getMessage());
        }

        return ces;
    }
}
