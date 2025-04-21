package devforge.susuManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
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

    // Relación muchos a muchos con susus
    @ManyToMany
    @JoinTable(
            name = "usuario_susu", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "idUsuario"), // Columna que referencia al usuario
            inverseJoinColumns = @JoinColumn(name = "idSusu") // Columna que referencia al susu
    )
    private List<Susus> susus;

    /*
    * Esta entidad es para la tabla Usuarios en la bd
    * */
}
