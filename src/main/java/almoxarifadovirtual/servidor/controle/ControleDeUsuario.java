package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.servico.ServicoUsuario;
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
public class ControleDeUsuario {

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
   */
  @PostMapping
  @ResponseBody
  public Usuario criarUsuario(@RequestBody Usuario usuario, @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    return servicoUsuario.create(usuario);
  }

  /**
   * Método para recuperar as informações de um usuário do sistema.
   *
   * @param id - Valor da id do usuário do tipo Long.
   * @param chave - String utilizada para validar a autorização do requerente em executar a ação.
   * @return O usuário correspondente a id contida na url.
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Usuario getUsuarioPelaId(@PathVariable("id") Long id, @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    controleDeAutenticacao.validarUsuarioId(chave, id);

    return servicoUsuario.getUsuarioPelaId(id);
  }

  /**
   * Método que atualiza as informações do usuário.
   *
   * @param usuario - Objeto com as informações atualizadas.
   * @param chave - Código de acesso do usuário que está solicitando a operação.
   * @return True caso as informações sejam atualizadas com sucesso.
   */
  @PutMapping("/{id}")
  @ResponseBody
  public boolean atualizarUsuario(@RequestBody Usuario usuario, @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    controleDeAutenticacao.validarUsuarioLdap(usuario.getNome());

    return servicoUsuario.update(usuario);
  }

  /**
   * Método que remove um usuário do sistema.
   *
   * @param id - Código de identificação do usuário a ser removido.
   * @param chave - Código de acesso do usuário que está solicitando a operação.
   * @return True caso a operação tenha sido realizada com sucesso.
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public boolean removerUsuario(@PathVariable("id") Long id, @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    controleDeAutenticacao.validarUsuarioId(chave, id);

    return servicoUsuario.delete(id);
  }

  /**
   * Método que recupera todos os usuários do sistema.
   *
   * @param chave - Identificação do usuário que está solicitando a informação
   * @return Um lista com todos os usuários cadastrados no sistema.
   */
  @GetMapping("/listar")
  @ResponseBody
  public List<Usuario> getAll(@RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    return servicoUsuario.getAll();
  }

  /**
   * Método que recupera uma lista de usuários que tenham a mesma função.
   *
   * @param funcaoUsuario - Função que será usada para filtrar os usurários.
   * @param chave - Código de acesso do solicitante.
   * @return Uma lista com todos os usuários que pertençam a mesma função.
   */
  @GetMapping("/listar/{funcaoUsuario}")
  @ResponseBody
  private List<Usuario> getUsuarioByFuncao(@PathVariable("funcaoUsuario") String funcaoUsuario,
      @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);

    return servicoUsuario.getUsuariosPelaFuncao(funcaoUsuario);
  }
}
