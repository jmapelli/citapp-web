package pe.lizard.citapp.auth;

import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioService;
import pe.lizard.citapp.util.Error;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthSignUpServlet", urlPatterns = "/auth/signup")
public class AuthSignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;

        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");

        UsuarioEntity ue = new UsuarioEntity();
        ue.setUsuario(usuario);
        ue.setClave(clave);
        ue.setNombres(nombres);
        ue.setApellidos(apellidos);
        ue.setCorreo(correo);

        UsuarioService us = new UsuarioService();
        try {
            us.crearDoctor(ue);
            response.sendRedirect(request.getContextPath() + "/auth");
        } catch (Exception e) {
            Error.handler(request, e);

            rd = request.getRequestDispatcher("/template/auth/signup.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/auth/signup.jsp");
        rd.forward(request, response);
    }
}
