package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.operacao.TipoDeOperacao;
import almoxarifadovirtual.servidor.modelo.produto.Produto;
import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import almoxarifadovirtual.servidor.repositorio.RepositorioDeReserva;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeReserva {

  @Autowired
  private RepositorioDeReserva repositorioDeReserva;

  @Autowired
  private ServicoDeProduto servicoDeProduto;

  @Autowired
  private ServicoDeOperacao servicoDeOperacao;

  public Reserva salvarReserva(Reserva reserva) {
    return repositorioDeReserva.save(reserva);
  }

  public Reserva concluirReserva(Long id) {

    Reserva reserva = repositorioDeReserva.getById(id);

    servicoDeProduto.validarRetirada(reserva.getCarrinho());

    for (Produto produto : reserva.getCarrinho()) {
      servicoDeProduto.retirarProduto(produto);
    }

    servicoDeOperacao.save(
        new Operacao(TipoDeOperacao.RETIRADA, LocalDateTime.now().toString(), reserva.getCarrinho(),
            reserva.getUsuarioId()));

    reserva.setEntregue(true);

    return reserva;
  }

  public Reserva atualizarReserva(Reserva reserva) {
    return repositorioDeReserva.save(reserva);
  }

  public List<Reserva> getReservas() {
    return repositorioDeReserva.getAll();
  }

}
