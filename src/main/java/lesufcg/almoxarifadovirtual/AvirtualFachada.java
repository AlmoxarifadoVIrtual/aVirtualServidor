package lesufcg.almoxarifadovirtual;

import lesufcg.almoxarifadovirtual.controle.Controle;
import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvirtualFachada {

    @Autowired
    private Controle controle;

    @RequestMapping(value="/usuario", method = RequestMethod.POST)
    @ResponseBody
    public Usuario create(@RequestBody Usuario usuario) {
        return controle.criarUsuario(usuario);
    }

    @RequestMapping(value="/usuario", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@RequestHeader Long id) {
        return controle.getUsuario(id);
    }

    @RequestMapping(value="/usuario/listar", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getAll() {
        return controle.getAllUsuarios();
    }

    @RequestMapping(value="/usuario", method = RequestMethod.PUT)
    @ResponseBody
    public boolean update(@RequestBody Usuario usuario) {
        return controle.atualizarUsuario(usuario);
    }

    @RequestMapping(value="/usuario", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delete(@RequestHeader Long id) {
        return controle.deletarUsuario(id);
    }

    @RequestMapping(value="/usuario/listar/administradores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAdministradores() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ADMINISTRADOR);
    }

    @RequestMapping(value="/usuario/listar/almoxarifes", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarAlmoxarifes() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.ALMOXARIFE);
    }

    @RequestMapping(value="/usuario/listar/prestadores", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> listarPrestadores() {
        return controle.getUsuarioByFuncao(FuncaoUsuario.PRESTADOR);
    }

}
