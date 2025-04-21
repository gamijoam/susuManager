package devforge.susuManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private int id;
    private String descripcion;
    private int monto;
    private LocalDate fecha;
}