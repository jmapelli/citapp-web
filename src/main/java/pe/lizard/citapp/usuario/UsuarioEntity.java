package pe.lizard.citapp.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@Data
@NoArgsConstructor
//Jpa
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    public static final int DOCTOR = 1;
    public static final int PACIENTE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String usuario;

    @Column(nullable = true)
    private String clave;

    @Column(nullable = false)
    private int rol;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = true)
    private String nroDocumento;
}
