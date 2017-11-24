package pe.lizard.citapp.cita;

import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.util.DateUtil;
import pe.lizard.citapp.util.Error;
import pe.lizard.citapp.util.IntegerUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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
        CitaService cs = new CitaService();
        String codigo = request.getParameter("valor");
        UsuarioEntity doctor = (UsuarioEntity) request.getSession().getAttribute("usuario");
        String cita = request.getParameter("cita");
        Integer estado = IntegerUtil.toInteger(request.getParameter("estado"));

        Long idCita = null;
        if (cita != null && !cita.isEmpty()) {
            idCita = Long.parseLong(cita);
        }

        try {
            if (idCita != null) {
                cs.cambiarEstado(estado, idCita);
            }

            request.setAttribute("citas", cs.findByCodigo(codigo, doctor.getId()));
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_citas_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CitaService cs = new CitaService();
        String nroDocumento = request.getParameter("valor");
        UsuarioEntity doctor = (UsuarioEntity) request.getSession().getAttribute("usuario");
        String cita = request.getParameter("cita");
        Integer estado = IntegerUtil.toInteger(request.getParameter("estado"));

        Long idCita = null;
        if (cita != null && !cita.isEmpty()) {
            idCita = Long.parseLong(cita);
        }

        try {
            if (idCita != null) {
                cs.cambiarEstado(estado, idCita);
            }

            request.setAttribute("citas", cs.findByNroDocumento(nroDocumento, doctor.getId()));
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_citas_result.jsp");
        rd.forward(request, response);
    }

}
