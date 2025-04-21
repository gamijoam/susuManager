package devforge.susuManager.repositorio;

import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    List<Usuario> findBySusus(Susus susu);
}
