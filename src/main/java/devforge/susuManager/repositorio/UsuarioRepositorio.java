package devforge.susuManager.repositorio;

import devforge.susuManager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
}
