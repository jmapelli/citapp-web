package pe.lizard.citapp.cita;

import pe.lizard.citapp.horario.HorarioEntity;
import pe.lizard.citapp.horario.HorarioService;
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
import java.util.List;

@WebServlet(name = "CitaReservarServlet", urlPatterns = "/cita/reservar")
public class CitaReservarServlet extends HttpServlet {

    public static final String ACTION_BUSCAR_DOCUMENTO = "buscarDocumento";
    public static final String ACTION_BUSCAR_HORARIO = "buscarHorario";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CitaService cs = new CitaService();

        String nroDocumento = (String) request.getParameter("nroDocumento");
        String fecha = (String) request.getParameter("fecha");
        String horario = (String) request.getParameter("horario");
        String doctor = ((UsuarioEntity) request.getSession().getAttribute("usuario")).getId().toString();

        try {
            CitaEntity cita = cs.reservar(nroDocumento, fecha, horario, doctor);
            request.setAttribute("cita", cita);
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/crear_reserva_result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case ACTION_BUSCAR_DOCUMENTO:
                this.doGetBuscarDocumento(request, response);
                break;
            case ACTION_BUSCAR_HORARIO:
                this.doGetBuscarHorario(request, response);
                break;
            default:
                this.doGetResevar(request, response);
                break;
        }
    }

    public void doGetResevar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/reservar.jsp");
        rd.forward(request, response);
    }

    public void doGetBuscarDocumento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nroDocumento = request.getParameter("nroDocumento");
        UsuarioEntity ue = new UsuarioService().findByNroDocumento(nroDocumento);
        request.setAttribute("paciente", ue);

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_documento_result.jsp");
        rd.forward(request, response);
    }

    public void doGetBuscarHorario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fecha = request.getParameter("fecha");
            UsuarioEntity doctor = (UsuarioEntity) request.getSession().getAttribute("usuario");

            List<HorarioEntity> horarios_disponibles = new HorarioService().horarioDisponbles(doctor.getId(), fecha);
            request.setAttribute("horarios_disponibles", horarios_disponibles);
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/buscar_horarios_result.jsp");
        rd.forward(request, response);
    }
}
