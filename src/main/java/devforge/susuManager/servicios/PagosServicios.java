package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.UsuarioDTO;
import devforge.susuManager.model.Pagos;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.repositorio.PagosRepositorio;
import devforge.susuManager.repositorio.SususRepositorio;
import devforge.susuManager.repositorio.UsuarioRepositorio;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagosServicios implements IPagosServicios {
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private SususRepositorio sususRepositorio;
    @Autowired private PagosRepositorio pagosRepositorio;
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(Usuario -> new UsuarioDTO(Usuario.getNombreUsuario(),Usuario))
                .collect(Collectors.toList());
    }
    @Override
    public List<SusuDTO> listarSusu(){
        List<Susus> susu = sususRepositorio.findAll();
        return susu.stream()
                .map(Susus -> new SusuDTO(Susus.getNombreSusu(),Susus))
                .collect(Collectors.toList());
    }
    @Override
    public List<Pagos> listarPagos() {
            List<Pagos> pagos = pagosRepositorio.findAll();
            pagos.forEach(pago -> {
                Hibernate.initialize(pago.getUsuario());
                Hibernate.initialize(pago.getSusuPagos());
            });
            return pagos;
    }
    @Override
    public void agregarPago(Pagos pagos) {
        pagosRepositorio.save(pagos);
    }

    @Override
    public void eliminarPago(Pagos pagos) {
        pagosRepositorio.delete(pagos);
    }


}
