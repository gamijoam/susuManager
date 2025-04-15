package devforge.susuManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nombreUsuario;
    private String telefonoUsuario;
    private String emailUsuario;

    //Relacion uno a muchos con pagos
    @OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Pagos> pagos;

    //Relacion uno a muchos con turnos
    @OneToMany(mappedBy = "usuarioTurno", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Turnos> turnos;
    /*
    * Esta entidad es para la tabla Usuarios en la bd
    * */
}
