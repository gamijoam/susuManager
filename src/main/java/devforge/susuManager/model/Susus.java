package devforge.susuManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Susus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSusu;
    private String nombreSusu;
    private Double montoFijo;
    private LocalDate fechaInicio;
    private String frecuencia;

    //Relacion uno a muchos pagos
    @OneToMany(mappedBy = "susuPagos", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Pagos> pagos;

    // Relacion uno a muchos con turnos
    @OneToMany(mappedBy = "susuTurnos", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Turnos> turno;

    // Relación muchos a muchos con usuarios
    @ManyToMany(mappedBy = "susus")
    private List<Usuario> usuarios;
    /*
    * Esta entidad es para la tabla Susus en la bd
    * */
}
