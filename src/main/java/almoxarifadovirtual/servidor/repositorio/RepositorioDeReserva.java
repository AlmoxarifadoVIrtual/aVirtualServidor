package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioDeReserva extends JpaRepository<Reserva, Long> {

}
