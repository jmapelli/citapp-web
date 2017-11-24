package pe.lizard.citapp.cita;

import pe.lizard.citapp.db.Connection;
import pe.lizard.citapp.horario.HorarioEntity;
import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Date;
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

    public CitaEntity cambiarEStado(int estado, Long cita) {
        CitaEntity entity = null;
        try {
            em = Connection.getInstance();

            entity = em.find(CitaEntity.class, cita);
            em.getTransaction().begin();
            entity.setEstado(estado);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning(e.getLocalizedMessage());
        }

        return entity;
    }

    public List<CitaEntity> findByFecha(Date fecha, Long idDoctor) {
        List<CitaEntity> ces = null;

        try {
            em = Connection.getInstance();
            em.getEntityManagerFactory().getCache().evictAll();

            String sql = "SELECT c FROM CitaEntity c " +
                    "INNER JOIN c.doctor d " +
                    "WHERE d.id = :idDoctor and c.fecha like :fecha and " +
                    "c.estado in (" + CitaService.CITA_RESERVADA + "," + CitaService.CITA_ATENDIDA + ") " +
                    "order by c.horario";

            Query q = em.createQuery(sql, CitaEntity.class);
            q.setParameter("idDoctor", idDoctor);
            q.setParameter("fecha", fecha, TemporalType.DATE);

            ces = (List<CitaEntity>) q.getResultList();
        } catch (Exception e) {
            LOG.warning(e.getMessage());
        }

        return ces;
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
                    "WHERE d.id = :idDoctor and p.nroDocumento like :nroDocumento ";

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
