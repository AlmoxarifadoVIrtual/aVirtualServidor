package lesufcg.almoxarifadovirtual;

import lesufcg.almoxarifadovirtual.controle.Controle;
import lesufcg.almoxarifadovirtual.modelo.autenticacao.Credenciais;
import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvirtualFachada {

    @Autowired
    private Controle controle;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Credenciais credenciais){
        return controle.logIn(credenciais);
    }

    //Métodos de Usuários
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @ResponseBody
    public Usuario criarUsuario(@RequestBody Usuario usuario, @RequestHeader String token) { return controle.criarUsuario(usuario, token);}

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@PathVariable Long id, @RequestHeader String token) {return controle.getUsuario(id, token);}

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getAll(@RequestHeader String token) {
        return controle.getAllUsuarios(token);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean update(@RequestBody Usuario usuario, @RequestHeader String token) {
        return controle.atualizarUsuario(usuario, token);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id, @RequestHeader String token) {
        controle.deletarUsuario(id, token);
    }

    @RequestMapping(value = "/usuarios/administradores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAdministradores(@RequestHeader String token) {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR, token);
    }

    @RequestMapping(value = "/usuario/almoxarifes", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAlmoxarifes(@RequestHeader String token) {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE, token);
    }

    @RequestMapping(value = "/usuario/prestadores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarPrestadores(@RequestHeader String token) {
        return controle.getUsuarioByFuncao(FuncaoUsuario.PRESTADOR, token);
    }
}
