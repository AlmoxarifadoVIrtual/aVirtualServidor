package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.excecoes.PermissaoException;
import almoxarifadovirtual.servidor.excecoes.TokenException;
import almoxarifadovirtual.servidor.excecoes.UsuarioException;
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

  protected Long getUsuarioId(String chave) {
    return servicoToken.getTokenByChave(chave).getUsuarioId();
  }

  protected void validarAcessoLdap(String login, String senha) {
    if (!servicoLdap.validarUsuarioLdap(login, senha)) {
      throw new UsuarioException();
    }
  }

  protected void validarUsuarioLdap(String login) {
    if (servicoLdap.existeUsuario(login)) {
      throw new UsuarioException();
    }
  }

  protected void validarAdmin(String chave) {

    Token token = servicoToken.validarToken(chave);
    if (token == null) {
      throw new TokenException();
    } else if (!servicoUsuario.validarAdmin(token.getUsuarioId())) {
      throw new PermissaoException();
    }
  }

  protected void validarAlmoxarifeOuAdmin(String chave) {

    Token token = servicoToken.validarToken(chave);
    if (token == null) {
      throw new TokenException();
    } else if (!servicoUsuario.validarAlmoxarife(token.getUsuarioId()) && !servicoUsuario
        .validarAdmin(token.getUsuarioId())) {
      throw new PermissaoException();
    }
  }

  protected void validarPrestador(String chave) {

    Token token = servicoToken.validarToken(chave);
    if (token == null) {
      throw new TokenException();
    } else if (!servicoUsuario.validarPrestador(token.getUsuarioId())) {
      throw new PermissaoException();
    }
  }

  protected void validarUsuarioOuAdmin(String chave, Long id) {

    Token token = servicoToken.validarToken(chave);
    if (token == null) {
      throw new TokenException();
    } else if ((!servicoUsuario.validarAdmin(token.getUsuarioId())
        && !servicoToken.validarUsuarioId(chave, id))) {
      throw new PermissaoException();
    }
  }

  public void validarUsuario(String chave) {

    Token token = servicoToken.getTokenByChave(chave);

    if (token == null) {
      throw new TokenException();
    }
  }

}
