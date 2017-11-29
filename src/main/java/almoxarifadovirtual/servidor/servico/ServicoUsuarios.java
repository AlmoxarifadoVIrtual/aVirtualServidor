package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.util.PermissaoException;
import almoxarifadovirtual.servidor.util.UsuarioException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicoUsuarios {

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
   * @throws UsuarioException Caso as credencias não estejam cadastradas no serviço LDAP.
   */
  public String logIn(Credenciais credenciais) {

    validarLdap(credenciais);

    Usuario usuario = servicoUsuario.get(credenciais.getLogin());
    validarUsuario(usuario);

    Token token = servicoAutenticacao.getTokenByUsuarioId(usuario.getId());

    if (token == null) {
      token = servicoAutenticacao.gerarToken(usuario.getId());

    } else if (!token.validarToken()) {
      servicoAutenticacao.deletarToken(token);
      token = servicoAutenticacao.gerarToken(usuario.getId());
    }

    return token.getChave();
  }

  private void validarLdap(Credenciais credenciais) {
    if (!servicoLdap.ehUsuarioLdap(credenciais)) {
      throw new UsuarioException();
    }
  }

  private void validarUsuario(Usuario usuario) {
    if (usuario == null) {
      throw new UsuarioException();
    }
  }

  /**
   * Método que realiza o logout do sistema removendo o token correspondente a chave.
   *
   * @param chave - Código de acesso do usuário.
   */
  public void logout(String chave) {
    Token token = servicoAutenticacao.getTokenByChave(chave);
    if (token != null) {
      servicoAutenticacao.deletarToken(token);
    }
  }

  //Métodos do Usuário

  /**
   * Método utilizado para realizar a criação de um novo usuário no sistema.
   *
   * @param usuario - Objeto contendo os dados do usuário a ser criado.
   * @param chave - String utilizada para validar a autorização do requerente em executar a ação.
   * @return Um objeto do tipo Usuario conforme armazenado pelo sistema.
   * @throws PermissaoException Caso a chave não seja validada pelo sistema, ou não corresponda a um
   *         usuário com poderes de administrador.
   */
  public Usuario criarUsuario(Usuario usuario, String chave) {
    if (servicoAutenticacao.validarToken(chave) && validarAdmin(chave)) {
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
   * @throws PermissaoException Caso a chave não esteja cadastrada no sistema e, não pertença a um
   *         administrador ou ao usuário cuja id passada no parâmetro.
   */
  public Usuario getUsuario(Long id, String chave) {

    if (servicoAutenticacao.validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
      return servicoUsuario.get(id);
    }
    return null;
  }

  /**
   * Método que recupera todos os usuários do sistema.
   *
   * @param chave - Identificação do usuário que está solicitando a informação
   * @return Um lista com todos os usuários cadastrados no sistema, se o solicitante for um
   *         administrador do sistema e a chave passada no parâmetro for válida.
   */
  public List<Usuario> getAllUsuarios(String chave) {

    if (servicoAutenticacao.validarToken(chave) && validarAdmin(chave)) {
      return servicoUsuario.getAll();
    }

    return null;
  }

  /**
   * Método que atualiza as informações do usuário.
   *
   * @param usuario - Objeto com as informações atualizadas.
   * @param chave - Código de acesso do usuário que está solicitando a operação.
   * @return True caso as informações sejam atualizadas com sucesso.
   * @throws PermissaoException Caso a chave não esteja cadastrada no sistema e, não pertença a um
   *         administrador ou ao usuário cuja id passada no parâmetro.
   */
  public boolean atualizarUsuario(Usuario usuario, String chave) {

    return servicoAutenticacao.validarToken(chave) && validarAdmin(chave)
           && servicoLdap.existeUsuario(usuario.getNome()) && servicoUsuario.update(usuario);

  }

  /**
   * Método que remove um usuário do sistema.
   *
   * @param id - Código de identificação do usuário a ser removido.
   * @param chave - Código de acesso do usuário que está solicitando a operação.
   * @return True caso a operação tenha sido realizada com sucesso.
   * @throws PermissaoException Caso a chave não esteja cadastrada no sistema e, não pertença a um
   *         administrador ou ao usuário cuja id passada no parâmetro.
   */
  public boolean deletarUsuario(Long id, String chave) {

    return servicoAutenticacao.validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))
        && servicoUsuario
        .delete(id);

  }

  /**
   * Método que recupera uma lista de usuários que tenham a mesma função.
   *
   * @param funcaoUsuario - Função que será usada para filtrar os usurários.
   * @param chave - Código de acesso do solicitante.
   * @return Uma lista com todos os usuários que pertençam a mesma função.
   * @throws PermissaoException Caso a chave não esteja cadastrada no sistema, ou não corresponda a
   *         um administrador.
   */
  public List<Usuario> getUsuarioByFuncao(FuncaoUsuario funcaoUsuario, String chave) {

    if (servicoAutenticacao.validarToken(chave) && validarAdmin(chave)) {
      return servicoUsuario.get(funcaoUsuario);
    }

    return null;
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

  /**
   * Método que verifica se o id informado corresponde ao usuário da chave.
   *
   * @param chave - Código de acesso do usuário.
   * @param id - Identificação do usuário do tipo Long.
   * @return - True se o id estiver cadastrado no token correspondente a chave.
   */
  private boolean validarId(String chave, Long id) {

    Token token = servicoAutenticacao.getTokenByChave(chave);
    if (token.getUsuarioId().equals(id)) {
      return true;
    } else {
      throw new PermissaoException();
    }
  }

}
