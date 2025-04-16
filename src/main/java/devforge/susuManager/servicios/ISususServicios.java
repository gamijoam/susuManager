package devforge.susuManager.servicios;

import devforge.susuManager.model.Susus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISususServicios {

    public void agregarSusu(Susus susus);

    public List<Susus> listaSusus();

    public void eliminarSusu(Susus susus);
}
