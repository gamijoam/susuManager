package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.ReporteDTO;
import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.UsuarioDTO;
import devforge.susuManager.model.Pagos;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IPagosServicios {
    public List<UsuarioDTO> listarUsuarios();
    public List<SusuDTO> listarSusu();
    public List<Pagos> listarPagos();
    public void agregarPago(Pagos pagos);
    public void eliminarPago(Pagos pagos);
    List<ReporteDTO> getEstadoFinanciero(Susus susu, LocalDate startDate, LocalDate endDate);

    List<ReporteDTO> getPagosPendientes(Susus susu, LocalDate startDate, LocalDate endDate);

    List<ReporteDTO> getHistorialDistribucion(Susus susu, LocalDate startDate, LocalDate endDate);
}
