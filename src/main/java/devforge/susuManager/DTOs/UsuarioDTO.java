package devforge.susuManager.DTOs;

import devforge.susuManager.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {
    private String nombre;
    private Usuario usuario;
    @Override
    public String toString() {
        return nombre; // Solo muestra el nombre en el ComboBox
    }
}