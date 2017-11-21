package pe.lizard.citapp.horario;

import pe.lizard.citapp.db.Connection;
import pe.lizard.citapp.usuario.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class HorarioRepository {

    private static final Logger LOG = Logger.getLogger(HorarioRepository.class.getName());
    private EntityManager em;

    public HorarioEntity findById(Long id) {
        HorarioEntity he = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select he from HorarioEntity he where he.id like :id");
            q.setParameter("id", id);

            he = (HorarioEntity) q.getSingleResult();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return he;
    }

    public List<HorarioEntity> findAvailable(Long idDoctor, Date fecha) {
        List<HorarioEntity> hes = null;

        try {
            em = Connection.getInstance();

            String sql = "select h.* from horario h " +
                    "left join cita c on c.HORARIO = h.ID and c.FECHA = ? and c.DOCTOR = ? " +
                    "where c.id is null";

            Query q = em.createNativeQuery(sql, HorarioEntity.class);
            q.setParameter(1, fecha);
            q.setParameter(2, idDoctor);

            hes = (List<HorarioEntity>) q.getResultList();
        } catch (Exception e) {
            LOG.warning(e.getMessage());
        }

        return hes;
    }

}
