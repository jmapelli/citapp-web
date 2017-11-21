package pe.lizard.citapp.cita;

import pe.lizard.citapp.horario.HorarioEntity;
import pe.lizard.citapp.horario.HorarioService;
import pe.lizard.citapp.usuario.UsuarioEntity;
import pe.lizard.citapp.usuario.UsuarioService;
import pe.lizard.citapp.util.DateUtil;

public class CitaService {

    public static final int CITA_RESERVADA = 1;
    public static final int CITA_ATENDIDA = 2;
    public static final int CITA_CANCELADA = 3;

    UsuarioService us = null;
    HorarioService hs = null;
    CitaRepository cr = null;

    public CitaEntity reservar(String nroDocumento, String fecha, String idHorario, String idDoctor) throws Exception {
        us = new UsuarioService();
        hs = new HorarioService();
        cr = new CitaRepository();

        this.validarReservaRequest(nroDocumento, fecha, idHorario);

        UsuarioEntity paciente = us.findByNroDocumento(nroDocumento);
        HorarioEntity horario = hs.findById(Long.parseLong(idHorario));
        UsuarioEntity doctor = us.findById(Long.parseLong(idDoctor));

        this.validarPaciente(paciente);
        this.validarHorario(horario);

        CitaEntity cita = new CitaEntity();
        cita.setFecha(DateUtil.toDate("yyyy-MM-dd", fecha));
        cita.setPaciente(paciente);
        cita.setHorario(horario);
        cita.setDoctor(doctor);
        cita.setEstado(CITA_RESERVADA);

        return cr.crear(cita);
    }

    private void validarReservaRequest(String nroDocumento, String fecha, String idHorario) throws Exception {
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new Exception("El nro. de documento es invalido");
        }

        if (fecha == null || fecha.isEmpty()) {
            throw new Exception("La fecha es invalida");
        }

        if (idHorario == null || idHorario.isEmpty()) {
            throw new Exception("El horario es invalida");
        }
    }

    private void validarPaciente(UsuarioEntity paciente) throws Exception {
        if (paciente == null) {
            throw new Exception("El paciente no existe");
        }
    }

    private void validarHorario(HorarioEntity horario) throws Exception {
        if (horario == null) {
            throw new Exception("El horario no existe");
        }
    }

}
