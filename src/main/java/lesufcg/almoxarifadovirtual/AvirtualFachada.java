package lesufcg.almoxarifadovirtual;

import lesufcg.almoxarifadovirtual.controle.Controle;
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

    //Métodos de Usuários
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(controle.criarUsuario(usuario));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(controle.getUsuario(id));
    }

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
