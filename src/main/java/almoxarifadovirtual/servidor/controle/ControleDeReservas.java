package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.reserva.Reserva;
import almoxarifadovirtual.servidor.servico.ServicoDeReserva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<Reserva> registrarReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

      controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
      return Utils.generateResponse(servicoDeReserva.salvarReserva(reserva));
  }

  @PostMapping("/concluir/{id}")
  @ResponseBody
  public ResponseEntity<Reserva> concluirReserva(@RequestHeader String chave, @PathVariable("id") Long id){

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
    return Utils.generateResponse(servicoDeReserva.concluirReserva(id));
  }

  @PostMapping("/atualizar")
  @ResponseBody
  public ResponseEntity<Reserva> atualizarReserva(@RequestHeader String chave, @RequestBody Reserva reserva){

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
    return Utils.generateResponse(servicoDeReserva.atualizarReserva(reserva));
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Reserva>> getReservas(@RequestHeader String chave){

    controleDeAutenticacao.validarUsuario(chave);
    return Utils.generateResponse(servicoDeReserva.getReservas());
  }

}
