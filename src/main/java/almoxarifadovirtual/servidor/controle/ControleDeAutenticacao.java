package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.servico.ServicoLdap;
import almoxarifadovirtual.servidor.servico.ServicoToken;
import almoxarifadovirtual.servidor.servico.ServicoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControleDeAutenticacao {

  @Autowired
  ServicoToken servicoToken;

  @Autowired
  ServicoLdap servicoLdap;

  @Autowired
  ServicoUsuario servicoUsuario;

  public boolean validarAcessoLdap(String nome, String senha) {
    return servicoLdap.ehUsuarioLdap(nome, senha);
  }

  public boolean validarUsuarioLdap(String nome) {
    return servicoLdap.existeUsuario(nome);
  }



  public boolean validarAdmin(String chave) {

    Token token = servicoToken.validarToken(chave);
    return servicoUsuario.validarAdmin(token.getUsuarioId());
  }

  public boolean validarAlmoxarife(String chave) {

    Token token = servicoToken.validarToken(chave);
    return servicoUsuario.validarAlmoxarife(token.getUsuarioId());

  }

  public boolean validarPrestador(String chave) {

    Token token = servicoToken.validarToken(chave);
    return servicoUsuario.validarPrestador(token.getUsuarioId());
  }

  public boolean validarId(String chave, Long id) {

    return servicoToken.validarUsuarioId(chave, id);
  }
}
