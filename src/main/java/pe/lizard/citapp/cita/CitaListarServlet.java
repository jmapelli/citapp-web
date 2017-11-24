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
import java.util.List;

@WebServlet(name = "CitaListarServlet", urlPatterns = "/cita/listar")
public class CitaListarServlet extends HttpServlet {

    public static final String ACTION_DAILY = "daily";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case ACTION_DAILY:
                this.doGetDaily(request, response);
                break;
            default:
                this.doGetListar(request, response);
                break;
        }
    }

    private void doGetListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/listar_citas.jsp");
        rd.forward(request, response);
    }

    private void doGetDaily(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CitaService cs = new CitaService();
        UsuarioEntity doctor = (UsuarioEntity) request.getSession().getAttribute("usuario");
        String fecha = request.getParameter("fecha");
        String cita = request.getParameter("cita");
        Integer estado = IntegerUtil.toInteger(request.getParameter("estado"));


        if (fecha == null || fecha.isEmpty()) {
            fecha = DateUtil.toString("yyyy-MM-dd", new Date());
        }

        Long idCita = null;
        if (cita != null && !cita.isEmpty()) {
            idCita = Long.parseLong(cita);
        }

        try {
            if (idCita != null) {
                cs.cambiarEstado(estado, idCita);
            }

            List<CitaEntity> citas = cs.findByFecha(fecha, doctor.getId());

            request.setAttribute("fecha", fecha);
            request.setAttribute("citas", citas);
        } catch (Exception e) {
            Error.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cita/listar_citas_daily.jsp");
        rd.forward(request, response);
    }

}
