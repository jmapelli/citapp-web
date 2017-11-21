package pe.lizard.citapp.cita;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CitaBuscarServlet", urlPatterns = "/cita/buscar")
public class CitaBuscarServlet extends HttpServlet {

    public static final String ACTION_FINDBYRESERVA = "findByReserva";
    public static final String ACTION_FINDBYPACIENTE = "findByPaciente";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case ACTION_FINDBYRESERVA:
                this.doGetFindByReserva(request, response);
                break;
            case ACTION_FINDBYPACIENTE:
                this.doGetFindByPaciente(request, response);
                break;
            default:
                this.doGetBuscar(request, response);
                break;
        }
    }

    private void doGetBuscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_citas.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_citas_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_citas_result.jsp");
        rd.forward(request, response);
    }

}
