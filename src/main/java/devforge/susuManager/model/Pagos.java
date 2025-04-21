package devforge.susuManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
    private Integer usuarioId; // (FK) al usuario que pago
    private Integer susuId; // (FK) del susu al que pertenece el pago
    private Integer monto;
    private LocalDate fechaPago;

    //Relacion muchos a uno con Usuario
    @ManyToOne
    @JoinColumn(name = "idUsuario" , nullable = false)
    private Usuario usuario;

    //Relacion muchos a uno con Susus
    @ManyToOne
    @JoinColumn(name = "idSusu" , nullable = false)
    private Susus susuPagos;


    /*
    * Esta entiedad registra los pagos
    * */

    /*
    * -----Tarea----
    * Colocar las (FK)
    * */

}
