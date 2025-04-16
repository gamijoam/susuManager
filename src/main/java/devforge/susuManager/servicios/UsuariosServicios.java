package devforge.susuManager.servicios;

import devforge.susuManager.model.Usuario;
import devforge.susuManager.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServicios implements IUsuarioServicios{

    @Autowired private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

}
