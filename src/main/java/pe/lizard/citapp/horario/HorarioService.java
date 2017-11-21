package pe.lizard.citapp.horario;

import pe.lizard.citapp.util.DateUtil;

import java.util.Date;
import java.util.List;

public class HorarioService {

    HorarioRepository horarioRepository;

    public HorarioEntity findById(Long id) {
        horarioRepository = new HorarioRepository();
        return horarioRepository.findById(id);
    }

    public List<HorarioEntity> horarioDisponbles(Long idDoctor, String fecha) {
        Date fecha_cita = DateUtil.toDate("yyyy-MM-dd", fecha);

        horarioRepository = new HorarioRepository();
        return horarioRepository.findAvailable(idDoctor, fecha_cita);
    }

}
