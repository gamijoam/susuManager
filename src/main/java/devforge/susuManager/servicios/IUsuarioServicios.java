package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.UsuarioDTOagg;
import devforge.susuManager.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioServicios {

    public void agregarUsuario(Usuario usuario);

    public List<Usuario> listarUsuarios();

    public void eliminarUsuario(Usuario usuario);
    public void asociarUsuarioASusu(Integer idUsuario,Integer idSusu);
    public List<UsuarioDTOagg> listarUsuario();
}
