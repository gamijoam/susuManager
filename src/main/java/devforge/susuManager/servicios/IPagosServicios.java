package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.SusuDTO;
import devforge.susuManager.DTOs.UsuarioDTO;
import devforge.susuManager.model.Pagos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPagosServicios {
    public List<UsuarioDTO> listarUsuarios();
    public List<SusuDTO> listarSusu();
    public List<Pagos> listarPagos();
    public void agregarPago(Pagos pagos);
   public void eliminarPago(Pagos pagos);

}
