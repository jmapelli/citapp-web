package pe.lizard.citapp.paciente;

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

@WebServlet(name = "PacienteCrearServlet", urlPatterns = "/paciente/crear")
public class PacienteCrearServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String nroDocumento = request.getParameter("nroDocumento");

        try {
            UsuarioEntity ue = new UsuarioEntity();
            ue.setNombres(nombres);
            ue.setApellidos(apellidos);
            ue.setCorreo(correo);
            ue.setNroDocumento(nroDocumento);

            ue = new UsuarioService().crearPaciente(ue);
            request.setAttribute("paciente", ue);
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/paciente/crear_paciente_result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/paciente/crear.jsp");
        rd.forward(request, response);
    }
}
