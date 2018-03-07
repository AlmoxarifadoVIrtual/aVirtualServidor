package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeReserva extends JpaRepository<Reserva, Long> {

  Reserva getById(Long id);

}
