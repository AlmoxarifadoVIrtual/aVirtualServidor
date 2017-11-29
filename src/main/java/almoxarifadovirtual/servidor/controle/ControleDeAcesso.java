package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.servico.ServicoToken;
import almoxarifadovirtual.servidor.servico.ServicoUsuario;
import almoxarifadovirtual.servidor.excecoes.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acesso")
public class ControleDeAcesso {

  @Autowired
  ControleDeAutenticacao controleDeAutenticacao;

  @Autowired
  ServicoToken  servicoToken;

  @Autowired
  ServicoUsuario servicoUsuario;

  /**
   * Método que valida o login e gera uma chave com duração de 01 hora para o usuário utilizar o
   * sistema.
   *
   * @param credenciais - Objeto que contém o login e a senha do usuário.
   * @return Uma chave do tipo String que será utilizada para validação do acesso do usuário ao
   *         sistema.
   * @throws UsuarioException Caso as credencias não estejam cadastradas no serviço LDAP.
   */
  @PostMapping
  @ResponseBody
  public String logIn(@RequestBody Credenciais credenciais) {

    controleDeAutenticacao.validarAcessoLdap(credenciais.getLogin(), credenciais.getSenha());

    Usuario usuario = servicoUsuario.get(credenciais.getLogin());
    validarUsuario(usuario);

    Token token = servicoToken.getTokenByUsuarioId(usuario.getId());

    if (token == null) {
      token = servicoToken.gerarToken(usuario.getId());

    } else if (!token.validarToken()) {
      servicoToken.deletarToken(token);
      token = servicoToken.gerarToken(usuario.getId());
    }

    return token.getChave();
  }

  /**
   * Método que realiza o logout do sistema removendo o token correspondente a chave.
   *
   * @param chave - Código de acesso do usuário.
   */
  @DeleteMapping
  @ResponseBody
  public void logout(@PathVariable("chave") String chave) {
    Token token = servicoToken.getTokenByChave(chave);
    if (token != null) {
      servicoToken.deletarToken(token);
    }
  }

  //Métodos privados

  private void validarUsuario(Usuario usuario) {
    if (usuario == null) {
      throw new UsuarioException();
    }
  }
}
