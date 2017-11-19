package pe.lizard.citapp.auth;

import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.util.Error;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthLoginServlet", urlPatterns = "/auth")
public class AuthLoginServlet extends HttpServlet {

    public static final String ACTION_LOGOUT = "logout";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;

        try {
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");

            AuthLoginService as = new AuthLoginService();
            UsuarioEntity ue = as.login(usuario, clave);

            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("usuario", ue);

            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            Error.handler(request, e);

            rd = request.getRequestDispatcher("/template/auth/login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case ACTION_LOGOUT:
                    request.getSession().invalidate();
                    break;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/auth/login.jsp");
        rd.forward(request, response);
    }
}
