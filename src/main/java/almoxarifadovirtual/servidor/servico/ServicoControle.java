package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.util.LoginException;
import almoxarifadovirtual.servidor.util.PermissaoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicoControle {

  @Autowired
  private ServicoUsuario servicoUsuario;

  @Autowired
  private ServicoAutenticacao servicoAutenticacao;

  @Autowired
  private ServicoLdap servicoLdap;

  /**
   * Método que valida o login e gera uma chave com duração de 01 hora para o usuário utilizar o
   * sistema.
   *
   * @param credenciais - Objeto que contém o login e a senha do usuário.
   * @return Uma chave do tipo String que será utilizada para validação do acesso do usuário ao
   *         sistema.
   */
  public String logIn(Credenciais credenciais) {
    if (!servicoLdap.ehUsuarioLdap(credenciais)) {
      throw new LoginException();
    }

    Usuario usuario = servicoUsuario.get(credenciais.getLogin());

    if (usuario != null) {
      Token token = servicoAutenticacao.gerarToken(usuario.getId());
      return token.getChave();
    } else {
      throw new LoginException();
    }

  }

  //Métodos do Usuário

  /**
   * Método utilizado para realizar a criação de um novo usuário no sistema.
   *
   * @param usuario - Objeto contendo os dados do usuário a ser criado.
   * @param chave - String utilizada para validar a autorização do requerente em executar a ação.
   * @return Um objeto do tipo Usuario conforme armazenado pelo sistema.
   */
  public Usuario criarUsuario(Usuario usuario, String chave) {
    if (validarToken(chave) && validarAdmin(chave)) {
      return servicoUsuario.create(usuario);
    }
    return null;
  }

  /**
   * Método para recuperar as informações de um usuário do sistema.
   *
   * @param id - Valor da id do usuário do tipo Long.
   * @param chave - String utilizada para validar a autorização do requerente em executar a ação.
   * @return Se o usuário existir, e a chave tiver autorização para a operação, o usuário relativo
   *         ao id é retornado, caso contrário o retorno é null.
   */
  public Usuario getUsuario(Long id, String chave) {

    if (validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
      return servicoUsuario.get(id);
    }

    return null;
  }

  public List<Usuario> getAllUsuarios(String chave) {

    if (validarToken(chave) && validarAdmin(chave)) {
      return servicoUsuario.getAll();
    }

    return null;
  }

  public boolean atualizarUsuario(Usuario usuario, String chave) {

    return validarToken(chave) && validarAdmin(chave) && servicoUsuario.update(usuario);

  }

  public boolean deletarUsuario(Long id, String chave) {

    return validarToken(chave) && (validarAdmin(chave) || validarId(chave, id)) && servicoUsuario
        .delete(id);

  }

  public List<Usuario> getUsuarioByFuncao(FuncaoUsuario tipo, String chave) {

    if (validarToken(chave) && validarAdmin(chave)) {
      return servicoUsuario.get(tipo);
    }

    return null;
  }

  //Métodos de autenticação

  public void deletarToken(Token token) {
    servicoAutenticacao.deletarToken(token);
  }

  public Token getTokenByChave(String chave) {
    return servicoAutenticacao.getTokenByChave(chave);
  }

  public Token getTokenByUsuarioId(Long usuarioId) {
    return servicoAutenticacao.getTokenByUsuarioId(usuarioId);
  }

  public Token gerarToken(Long usuarioId) {
    return servicoAutenticacao.gerarToken(usuarioId);
  }

  private boolean validarToken(String chave) {
    return this.servicoAutenticacao.validarToken(chave);
  }

  // Métodos auxiliares de validação

  private boolean validarAdmin(String chave) {

    Token token = servicoAutenticacao.getTokenByChave(chave);
    Usuario usuario = servicoUsuario.get(token.getUsuarioId());

    if (usuario.getFuncao() == FuncaoUsuario.ADMINISTRADOR) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ADMINISTRADOR);
    }
  }

  private boolean validarAlmoxarife(String chave) {

    Token token = servicoAutenticacao.getTokenByChave(chave);
    Usuario usuario = servicoUsuario.get(token.getUsuarioId());

    if (usuario.getFuncao() == FuncaoUsuario.ALMOXARIFE) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ALMOXARIFE);
    }
  }

  private boolean validarPrestador(String chave) {

    Token token = servicoAutenticacao.getTokenByChave(chave);
    Usuario usuario = servicoUsuario.get(token.getUsuarioId());

    if (usuario.getFuncao() == FuncaoUsuario.PRESTADOR) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.PRESTADOR);
    }
  }

  private boolean validarId(String chave, Long id) {

    Token token = servicoAutenticacao.getTokenByChave(chave);
    if (token.getUsuarioId().equals(id)) {
      return true;
    } else {
      throw new PermissaoException();
    }
  }

  public void logout(String chave) {
    Token token = servicoAutenticacao.getTokenByChave(chave);
    if (token != null) {
      servicoAutenticacao.deletarToken(token);
    }
  }
}
