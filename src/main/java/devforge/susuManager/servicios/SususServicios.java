package devforge.susuManager.servicios;

import devforge.susuManager.model.Susus;
import devforge.susuManager.repositorio.SususRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SususServicios implements ISususServicios{

    @Autowired private SususRepositorio sususRepositorio;

    @Override
    public void agregarSusu(Susus susus) {
        sususRepositorio.save(susus);
    }

    @Override
    public List<Susus> listaSusus() {
        return sususRepositorio.findAll();
    }

    @Override
    public void eliminarSusu(Susus susus) {
        sususRepositorio.delete(susus);
    }
}
