package devforge.susuManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SusuDTO2 {
    private Integer idSusu;
    private String nombreSusu;
    @Override
    public String toString() {
        return nombreSusu; // Solo muestra el nombre del susu en el ComboBox
    }
}
