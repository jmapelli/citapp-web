package pe.lizard.citapp.paciente;

import pe.lizard.citapp.usuario.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PacienteListarServlet", urlPatterns = "/paciente")
public class PacienteListarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pacientes", new UsuarioService().findPacientes());
        RequestDispatcher rd = request.getRequestDispatcher("/template/paciente/listar.jsp");
        rd.forward(request, response);
    }
}
