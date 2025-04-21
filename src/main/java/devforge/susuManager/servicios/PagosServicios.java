package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.ReporteDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    @Transactional
    public void agregarPago(Pagos pago) {
        // Obtener el usuario y el susu del pago
        Usuario usuario = usuarioRepositorio.findById(pago.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Susus susu = sususRepositorio.findById(pago.getSusuPagos().getIdSusu())
                .orElseThrow(() -> new RuntimeException("Susu no encontrado"));

        // Inicializar las relaciones lazy
        Hibernate.initialize(usuario.getSusus());

        // Verificar si el usuario está asociado al susu
        if (!usuario.getSusus().contains(susu)) {
            throw new RuntimeException("El usuario no está asociado a este susu.");
        }

        // Guardar el pago si la validación es exitosa
        pagosRepositorio.save(pago);
    }

    @Override
    public void eliminarPago(Pagos pagos) {
        pagosRepositorio.delete(pagos);
    }

    @Override
    public List<ReporteDTO> getEstadoFinanciero(Susus susu, LocalDate startDate, LocalDate endDate) {
        List<Pagos> pagos = pagosRepositorio.findBySusuPagosAndFechaPagoBetween(susu, startDate, endDate);

        return pagos.stream()
                .map(pago -> new ReporteDTO(
                        pago.getIdPago(),
                        "Pago registrado " + pago.getUsuario().getNombreUsuario(),
                        pago.getMonto(),
                        pago.getFechaPago()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteDTO> getPagosPendientes(Susus susu, LocalDate startDate, LocalDate endDate) {
        List<Usuario> usuarios = usuarioRepositorio.findBySusus(susu);

        return usuarios.stream()
                .filter(usuario -> pagosRepositorio.findByUsuarioAndSusuPagosAndFechaPagoBetween(usuario, susu, startDate, endDate).isEmpty())
                .map(usuario -> new ReporteDTO(
                        usuario.getIdUsuario(),
                        "Pago pendiente de " + usuario.getNombreUsuario(),
                        0,
                        null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteDTO> getHistorialDistribucion(Susus susu, LocalDate startDate, LocalDate endDate) {
        List<Pagos> pagos = pagosRepositorio.findBySusuPagosAndFechaPagoBetween(susu, startDate, endDate);

        return pagos.stream()
                .map(pago -> new ReporteDTO(
                        pago.getIdPago(),
                        "Monto distribuido a " + pago.getUsuario().getNombreUsuario(),
                        pago.getMonto(),
                        pago.getFechaPago()
                ))
                .collect(Collectors.toList());
    }

}
