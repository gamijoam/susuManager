package devforge.susuManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTurnos;
    private Integer susuId; // (FK) id del susu
    private Integer usuarioId; // (FK) id del usuario que recibira el monto
    private Date fechaTurno; // Fecha en que el usuario recibira el monto
    private String estado; // Estado del turno (Pendiente,completado)

    // Relacion muchos a uno con susu
    @ManyToOne
    @JoinColumn(name = "idSusu",nullable = false)
    private Susus susuTurnos;

    //relacion muchos a uno con usuario
    @ManyToOne
    @JoinColumn(name = "idUsuario" , nullable = false)
    private Usuario usuarioTurno;

    /*
    * Esta entidad es para los turnos
    * */
}
