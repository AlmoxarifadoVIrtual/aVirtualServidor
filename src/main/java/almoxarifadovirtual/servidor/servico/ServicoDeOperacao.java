package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.operacao.TipoDeOperacao;
import almoxarifadovirtual.servidor.repositorio.RepositorioDeOperacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeOperacao {

  @Autowired
  RepositorioDeOperacao repositorioDeOperacao;


  public List<Operacao> findByUsuarioId(
      Long usuarioId) {
    return repositorioDeOperacao.findByUsuarioId(usuarioId);
  }

  public List<Operacao> findByTipoDeOperacao(String tipoDeOperacao) {
    return repositorioDeOperacao
        .findByTipoDeOperacao(TipoDeOperacao.selecionarTipo(tipoDeOperacao));
  }

  public List<Operacao> findAll() {
    return repositorioDeOperacao.findAll();
  }

  public Operacao save(Operacao operacao) {
    return repositorioDeOperacao.save(operacao);
  }

}
