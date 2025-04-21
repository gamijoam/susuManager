package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.SusuDTO2;
import devforge.susuManager.DTOs.SusuDTOagg;
import devforge.susuManager.model.Susus;
import devforge.susuManager.repositorio.SususRepositorio;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SususServicios implements ISususServicios{

    @Autowired private SususRepositorio sususRepositorio;

    @Override
    public void agregarSusu(Susus susus) {
        sususRepositorio.save(susus);
    }

    @Override
    @Transactional
    public List<SusuDTO2> listaSusus() {
        List<Susus> susus = sususRepositorio.findAll();

        return susus.stream()
                .peek(susu -> Hibernate.initialize(susu.getPagos())) // Inicializa la colección de pagos
                .map(susu -> new SusuDTO2(susu.getIdSusu(), susu.getNombreSusu()))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarSusu(Susus susus) {
        sususRepositorio.delete(susus);
    }

    @Override
    public List<Susus> listarSusus() {
        return sususRepositorio.findAll();
    }

    @Override
    public List<SusuDTOagg> listaSususs() {
        List<Susus> susu = sususRepositorio.findAll();
        return susu.stream()
                .map(susus -> new SusuDTOagg(susus.getIdSusu(),susus.getNombreSusu()))
                .collect(Collectors.toList());
    }

}

