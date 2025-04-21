package devforge.susuManager.servicios;

import devforge.susuManager.DTOs.UsuarioDTOagg;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import devforge.susuManager.repositorio.SususRepositorio;
import devforge.susuManager.repositorio.UsuarioRepositorio;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosServicios implements IUsuarioServicios{

    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private SususRepositorio sususRepositorio;

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


    // Método para asociar un usuario a un susu
    @Override
    @Transactional
    public void asociarUsuarioASusu(Integer idUsuario, Integer idSusu) {
        // Obtener el usuario y el susu por sus IDs
        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Susus susu = sususRepositorio.findById(idSusu)
                .orElseThrow(() -> new RuntimeException("Susu no encontrado"));

        // Inicializar las relaciones lazy
        Hibernate.initialize(usuario.getSusus()); // Inicializa la colección de susus del usuario
        Hibernate.initialize(susu.getUsuarios()); // Inicializa la colección de usuarios del susu

        // Asociar el susu al usuario
        usuario.getSusus().add(susu);

        // Guardar el usuario actualizado
        usuarioRepositorio.save(usuario);
    }

    @Override
    public List<UsuarioDTOagg> listarUsuario() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTOagg(usuario.getIdUsuario(), usuario.getNombreUsuario()))
                .collect(Collectors.toList());
    }


}
