package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import almoxarifadovirtual.servidor.servico.ServicoDeReserva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ControleDeReservas {

  @Autowired
  ControleDeAutenticacao controleDeAutenticacao;

  @Autowired
  private ServicoDeReserva servicoDeReserva;

  @PostMapping
  @ResponseBody
  public Reserva registrarReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

      controleDeAutenticacao.validarPrestador(chave);
      return servicoDeReserva.salvarReserva(reserva);
  }

  @PostMapping
  @ResponseBody
  public Reserva concluirReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
    return servicoDeReserva.concluirReserva(reserva);
  }

  @PostMapping
  @ResponseBody
  public Reserva liberarReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
    return servicoDeReserva.liberarReserva(reserva);
  }

  @PostMapping
  @ResponseBody
  public Reserva atualizarReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

    controleDeAutenticacao.validarPrestador(chave);
    return servicoDeReserva.atualizarReserva(reserva);
  }

  public List<Reserva> getReservas(@RequestHeader String chave){
    controleDeAutenticacao.validarUsuario(chave);
    return servicoDeReserva.getReservas();
  }

}
