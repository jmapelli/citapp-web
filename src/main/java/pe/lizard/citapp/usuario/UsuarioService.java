package pe.lizard.citapp.usuario;

public class UsuarioService {

    UsuarioRepository us;

    private UsuarioEntity crearUsuario(UsuarioEntity entity) throws Exception {
        this.validarCorreo(entity.getCorreo());

        us = new UsuarioRepository();
        return us.crear(entity);
    }

    public UsuarioEntity crearDoctor(UsuarioEntity entity) throws Exception {
        this.validarUsuario(entity.getUsuario());

        entity.setRol(UsuarioEntity.DOCTOR);
        return this.crearUsuario(entity);
    }

    public UsuarioEntity crearPaciente(UsuarioEntity entity) throws Exception {
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
