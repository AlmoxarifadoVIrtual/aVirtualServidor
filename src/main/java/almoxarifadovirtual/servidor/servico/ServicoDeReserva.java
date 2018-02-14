package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import almoxarifadovirtual.servidor.repositorio.RepositorioDeReserva;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeReserva {

  private RepositorioDeReserva repositorioDeReserva;

  public Reserva salvarReserva(Reserva reserva){
    return repositorioDeReserva.save(reserva);
  }


  public Reserva concluirReserva(Reserva reserva) {
    return null;
  }

  public Reserva liberarReserva(Reserva reserva) {
    return null;
  }

  public Reserva atualizarReserva(Reserva reserva) {
    return null;
  }

  public List<Reserva> getReservas() {
    return null;
  }
}
