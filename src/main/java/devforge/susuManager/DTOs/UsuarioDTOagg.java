package devforge.susuManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTOagg {
    private Integer idUsuario;
    private String nombreUsuario;
    @Override
    public String toString() {
        return nombreUsuario; // Solo muestra el nombre en el ComboBox
    }
}
