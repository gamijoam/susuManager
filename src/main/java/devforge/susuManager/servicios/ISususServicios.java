package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.SusuDTO2;
import devforge.susuManager.DTOs.SusuDTOagg;
import devforge.susuManager.model.Susus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISususServicios {

    public void agregarSusu(Susus susus);

    public List<SusuDTO2> listaSusus();

    public void eliminarSusu(Susus susus);
    public List<Susus> listarSusus();
    public List<SusuDTOagg> listaSususs();
}
