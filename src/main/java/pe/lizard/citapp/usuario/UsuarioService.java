package pe.lizard.citapp.usuario;

import java.util.List;

public class UsuarioService {

    UsuarioRepository us;

    public UsuarioEntity findById(Long id) {
        return us.findById(id);
    }

    public List<UsuarioEntity> findPacientes() {
        us = new UsuarioRepository();
        return us.findUsuarios(UsuarioEntity.PACIENTE);
    }

    public UsuarioEntity crearDoctor(UsuarioEntity entity) throws Exception {
        this.validarNombresApellidos(entity.getNombres(), entity.getApellidos());
        this.validarUsuario(entity.getUsuario());
        this.validarCorreo(entity.getCorreo());

        entity.setRol(UsuarioEntity.DOCTOR);
        return this.crearUsuario(entity);
    }

    public UsuarioEntity crearPaciente(UsuarioEntity entity) throws Exception {
        this.validarNombresApellidos(entity.getNombres(), entity.getApellidos());
        this.validarCorreo(entity.getCorreo());
        this.validarNroDocumento(entity.getNroDocumento());

        entity.setRol(UsuarioEntity.PACIENTE);
        return this.crearUsuario(entity);
    }

    public UsuarioEntity findByCorreo(String correo) {
        us = new UsuarioRepository();
        return us.findByCorreo(correo);
    }

    public UsuarioEntity findByUsuario(String usuario) {
        us = new UsuarioRepository();
        return us.findByUsuario(usuario);
    }

    public UsuarioEntity findByNroDocumento(String nroDocumento) {
        us = new UsuarioRepository();
        return us.findByNroDocumento(nroDocumento);
    }

    private UsuarioEntity crearUsuario(UsuarioEntity entity) throws Exception {
        us = new UsuarioRepository();
        return us.crear(entity);
    }


    private void validarNombresApellidos(String nombres, String apellidos) throws Exception {
        if (nombres == null || nombres.isEmpty()) {
            throw new Exception("Los nombres son invalido");
        }

        if (apellidos == null || apellidos.isEmpty()) {
            throw new Exception("Los apellidos son invalido");
        }
    }

    private void validarCorreo(String correo) throws Exception {
        if (correo == null || correo.isEmpty()) {
            throw new Exception("El correo es invalido");
        }

        if (this.findByCorreo(correo) != null) {
            throw new Exception("El correo ya existe");
        }
    }

    private void validarUsuario(String usuario) throws Exception {
        if (usuario == null || usuario.isEmpty()) {
            throw new Exception("El usuario es invalido");
        }

        if (this.findByUsuario(usuario) != null) {
            throw new Exception("El usuario ya existe");
        }
    }

    private void validarNroDocumento(String nroDocumento) throws Exception {
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new Exception("El nro. de documento es invalido");
        }

        if (this.findByNroDocumento(nroDocumento) != null) {
            throw new Exception("El nro. de documento ya existe");
        }
    }

}
