package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.servico.ServicoUsuarios;
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
public class UsuariosControle {

  @Autowired
  private ServicoUsuarios servicoUsuarios;

  @PostMapping("/login")
  @ResponseBody
     public String login(@RequestBody Credenciais credenciais) {
    return servicoUsuarios.logIn(credenciais);
  }

  @DeleteMapping("/logout")
  @ResponseBody
  public void logout(@PathVariable("chave") String chave) {
    servicoUsuarios.logout(chave);
  }

  //Métodos de Usuários
  @PostMapping
  @ResponseBody
  public Usuario criarUsuario(@RequestBody Usuario usuario, @RequestHeader String token) {
    return servicoUsuarios.criarUsuario(usuario, token);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Usuario getUsuario(@PathVariable("id") Long id, @RequestHeader String token) {
    return servicoUsuarios.getUsuario(id, token);
  }

  @GetMapping
  @ResponseBody
  public List<Usuario> getAll(@RequestHeader String token) {
    return servicoUsuarios.getAllUsuarios(token);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public boolean update(@RequestBody Usuario usuario, @RequestHeader String token) {
    return servicoUsuarios.atualizarUsuario(usuario, token);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public void delete(@PathVariable("id") Long id, @RequestHeader String token) {
    servicoUsuarios.deletarUsuario(id, token);
  }

  @GetMapping("/administradores")
  @ResponseBody
  public List<Usuario> listarAdministradores(@RequestHeader String token) {
    return servicoUsuarios.getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR, token);
  }

  @GetMapping("/almoxarifes")
  @ResponseBody
  public List<Usuario> listarAlmoxarifes(@RequestHeader String token) {
    return servicoUsuarios.getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE, token);
  }

  @GetMapping("/prestadores")
  @ResponseBody
  public List<Usuario> listarPrestadores(@RequestHeader String token) {
    return servicoUsuarios.getUsuarioByFuncao(FuncaoUsuario.PRESTADOR, token);
  }
}
