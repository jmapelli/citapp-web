package pe.lizard.citapp.auth;

import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioService;

public class AuthLoginService {

    UsuarioService us = null;

    public UsuarioEntity login(String usuario, String clave) throws Exception {
        this.validateRequest(usuario, clave);

        us = new UsuarioService();
        UsuarioEntity ue = us.findByUsuario(usuario);

        this.validateUsername(ue);
        this.validatePassword(ue.getClave(), clave);

        return ue;
    }

    private void validateRequest(String usuario, String clave) throws Exception {
        if (usuario == null || clave == null || usuario.isEmpty() || clave.isEmpty()) {
            throw new Exception("Los datos son invalidos");
        }
    }

    private void validateUsername(UsuarioEntity ue) throws Exception {
        if (ue == null) {
            throw new Exception("El usuario no existe");
        }
    }

    private void validatePassword(String clave_guardada, String clave_request) throws Exception {
        if (!clave_guardada.equals(clave_request)) {
            throw new Exception("La contraseña es incorrecta");
        }
    }

}
