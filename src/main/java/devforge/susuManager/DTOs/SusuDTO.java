package devforge.susuManager.DTOs;

import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SusuDTO {
    private String nombre;
    private Susus susus;
    @Override
    public String toString() {
        return nombre; // Solo muestra el nombre en el ComboBox
    }
}
