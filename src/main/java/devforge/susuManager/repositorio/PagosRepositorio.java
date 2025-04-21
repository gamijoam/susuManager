package devforge.susuManager.repositorio;

import devforge.susuManager.model.Pagos;
import devforge.susuManager.model.Susus;
import devforge.susuManager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface PagosRepositorio extends JpaRepository<Pagos,Integer> {

    // Consulta para obtener pagos por susu y rango de fechas
    List<Pagos> findBySusuPagosAndFechaPagoBetween(Susus susu, LocalDate startDate, LocalDate endDate);

    // Consulta para verificar si un usuario tiene pagos en un susu y rango de fechas
    List<Pagos> findByUsuarioAndSusuPagosAndFechaPagoBetween(Usuario usuario, Susus susu, LocalDate startDate, LocalDate endDate);

}
