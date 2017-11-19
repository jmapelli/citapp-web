package pe.lizard.citapp.cita;

import lombok.Data;
import lombok.NoArgsConstructor;
import pe.lizard.citapp.horario.HorarioEntity;
import pe.lizard.citapp.usuario.UsuarioEntity;

import javax.persistence.*;
import java.util.Date;

//Lombok
@Data
@NoArgsConstructor
//Jpa
@Entity
@Table(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = UsuarioEntity.class)
    @JoinColumn(name = "PACIENTE", nullable = false)
    private UsuarioEntity paciente;

    @ManyToOne(targetEntity = UsuarioEntity.class)
    @JoinColumn(name = "DOCTOR", nullable = false)
    private UsuarioEntity doctor;

    @ManyToOne(targetEntity = HorarioEntity.class)
    @JoinColumn(name = "HORARIO", nullable = false)
    private HorarioEntity horario;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String estado;

}
