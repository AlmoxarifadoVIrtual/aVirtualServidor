package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.servico.ServicoControle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AvirtualFachada {

  @Autowired
  private ServicoControle servicoControle;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public String login(@RequestBody Credenciais credenciais) {
    return servicoControle.logIn(credenciais);
  }

  //Métodos de Usuários
  @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
  @ResponseBody
  public Usuario criarUsuario(@RequestBody Usuario usuario, @RequestHeader String token) {
    return servicoControle.criarUsuario(usuario, token);
  }

  @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Usuario getUsuario(@PathVariable("id") Long id, @RequestHeader String token) {
    return servicoControle.getUsuario(id, token);
  }

  @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
  @ResponseBody
  public List<Usuario> getAll(@RequestHeader String token) {
    return servicoControle.getAllUsuarios(token);
  }

  @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public boolean update(@RequestBody Usuario usuario, @RequestHeader String token) {
    return servicoControle.atualizarUsuario(usuario, token);
  }

  @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public void delete(@PathVariable("id") Long id, @RequestHeader String token) {
    servicoControle.deletarUsuario(id, token);
  }

  @RequestMapping(value = "/usuarios/administradores", method = RequestMethod.GET)
  @ResponseBody
  public List<Usuario> listarAdministradores(@RequestHeader String token) {
    return servicoControle.getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR, token);
  }

  @RequestMapping(value = "/usuario/almoxarifes", method = RequestMethod.GET)
  @ResponseBody
  public List<Usuario> listarAlmoxarifes(@RequestHeader String token) {
    return servicoControle.getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE, token);
  }

  @RequestMapping(value = "/usuario/prestadores", method = RequestMethod.GET)
  @ResponseBody
  public List<Usuario> listarPrestadores(@RequestHeader String token) {
    return servicoControle.getUsuarioByFuncao(FuncaoUsuario.PRESTADOR, token);
  }
}
