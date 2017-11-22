package lesufcg.almoxarifadovirtual.controle;

import lesufcg.almoxarifadovirtual.service.Controle;
import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvirtualFachada {

    @Autowired
    private Controle controle;

    //Métodos de Usuários
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @ResponseBody
    public Usuario criarUsuario(@RequestBody Usuario usuario) { return controle.criarUsuario(usuario);}

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@PathVariable Long id) {return controle.getUsuario(id);}

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getAll() {
        return controle.getAllUsuarios();
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean update(@PathVariable Usuario usuario) {
        return controle.atualizarUsuario(usuario);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        controle.deletarUsuario(id);
    }

    @RequestMapping(value = "/usuarios/administradores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAdministradores() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR);
    }

    @RequestMapping(value = "/usuario/almoxarifes", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAlmoxarifes() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE);
    }

    @RequestMapping(value = "/usuario/prestadores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarPrestadores() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.PRESTADOR);
    }
}