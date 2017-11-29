package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.servico.ServicoUsuario;
import almoxarifadovirtual.servidor.excecoes.PermissaoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")

public class ControleDeUsuarios {

  @Autowired
  private ServicoUsuario servicoUsuario;

  @Autowired
  private ControleDeAutenticacao controleDeAutenticacao;

  /**
   * Método utilizado para realizar a criação de um novo usuário no sistema.
   *
   * @param usuario - Objeto contendo os dados do usuário a ser criado.
   * @param chave - String utilizada para validar a autorização do requerente em executar a ação.
   * @return Um objeto do tipo Usuario conforme armazenado pelo sistema.
   * @throws PermissaoException Caso a chave não seja validada pelo sistema, ou não corresponda a um
   *         usuário com poderes de administrador.
   */
  @PostMapping
  @ResponseBody
  public Usuario criarUsuario(@RequestBody Usuario usuario, @RequestHeader String chave) {
    if (controleDeAutenticacao.validarAdmin(chave)) {
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
  @GetMapping("/{id}")
  @ResponseBody
  public Usuario getUsuario(@PathVariable("id") Long id, @RequestHeader String chave) {
    if (controleDeAutenticacao.validarAdmin(chave) || controleDeAutenticacao.validarId(chave, id)) {
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
  @GetMapping
  @ResponseBody
  public List<Usuario> getAll(@RequestHeader String chave) {
    if (controleDeAutenticacao.validarAdmin(chave)) {
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
  @PutMapping("/{id}")
  @ResponseBody
  public boolean atualizarUsuario(@RequestBody Usuario usuario, @RequestHeader String chave) {

    return controleDeAutenticacao.validarAdmin(chave)
           && controleDeAutenticacao.validarUsuarioLdap(usuario.getNome())
           && servicoUsuario.update(usuario);
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
  @DeleteMapping("/{id}")
  @ResponseBody
  public boolean removerUsuario(@PathVariable("id") Long id, @RequestHeader String chave) {

    return (controleDeAutenticacao.validarAdmin(chave)
           || controleDeAutenticacao.validarId(chave, id)) && servicoUsuario.delete(id);

  }

  @GetMapping("/administradores")
  @ResponseBody
  public List<Usuario> listarAdministradores(@RequestHeader String token) {
    return getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR, token);
  }

  @GetMapping("/almoxarifes")
  @ResponseBody
  public List<Usuario> listarAlmoxarifes(@RequestHeader String token) {
    return getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE, token);
  }

  @GetMapping("/prestadores")
  @ResponseBody
  public List<Usuario> listarPrestadores(@RequestHeader String token) {
    return getUsuarioByFuncao(FuncaoUsuario.PRESTADOR, token);
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
  private List<Usuario> getUsuarioByFuncao(FuncaoUsuario funcaoUsuario, String chave) {

    if (controleDeAutenticacao.validarAdmin(chave)) {
      return servicoUsuario.get(funcaoUsuario);
    }

    return null;
  }
}
