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

    @Column(nullable = false, unique = true)
    private String codigo;

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
    private int estado;

    @PrePersist
    private void prePersist() {
        this.codigo = "C" + new Date().getTime();
    }

}
