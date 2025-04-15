package devforge.susuManager.repositorio;

import devforge.susuManager.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PagosRepositorio extends JpaRepository<Pagos,Integer> {
}
