package devforge.susuManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SusuDTOagg {
    private Integer idSusu;
    private String nombreSusu;

    @Override
    public String toString(){
        return nombreSusu;
    }
}
