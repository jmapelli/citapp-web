package pe.lizard.citapp.usuario;

import pe.lizard.citapp.db.Connection;
import pe.lizard.citapp.horario.HorarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioRepository {

    private static final Logger LOG = Logger.getLogger(UsuarioRepository.class.getName());
    private EntityManager em;

    public UsuarioEntity findById(Long id) {
        UsuarioEntity ue = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select ue from UsuarioEntity ue where ue.id like :id");
            q.setParameter("id", id);

            ue = (UsuarioEntity) q.getSingleResult();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return ue;
    }

    public UsuarioEntity crear(UsuarioEntity entity) {
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


    public UsuarioEntity findByCorreo(String correo) {
        UsuarioEntity ue = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select ue from UsuarioEntity ue where ue.correo like :correo");
            q.setParameter("correo", correo);

            ue = (UsuarioEntity) q.getSingleResult();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return ue;
    }

    public UsuarioEntity findByUsuario(String usuario) {
        UsuarioEntity ue = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select ue from UsuarioEntity ue where ue.usuario like :usuario");
            q.setParameter("usuario", usuario);

            ue = (UsuarioEntity) q.getSingleResult();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return ue;
    }

    public UsuarioEntity findByNroDocumento(String nroDocumento) {
        UsuarioEntity ue = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select ue from UsuarioEntity ue where ue.nroDocumento like :nroDocumento");
            q.setParameter("nroDocumento", nroDocumento);

            ue = (UsuarioEntity) q.getSingleResult();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return ue;
    }

    public List<UsuarioEntity> findUsuarios(int rol) {
        List<UsuarioEntity> ues = null;

        try {
            em = Connection.getInstance();

            Query q = em.createQuery("Select ue from UsuarioEntity ue where ue.rol like :rol");
            q.setParameter("rol", rol);

            ues = (List<UsuarioEntity>) q.getResultList();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return ues;
    }

}
