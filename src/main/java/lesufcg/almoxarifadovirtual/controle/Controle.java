package lesufcg.almoxarifadovirtual.controle;

import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Controle {

    @Autowired
    private ControleUsuario controleUsuario;


    //Métodos do Usuário

    public Usuario criarUsuario(Usuario usuario) {
        return controleUsuario.create(usuario);
    }

    public Usuario getUsuario(Long id) {
        return controleUsuario.get(id);
    }

    public List<Usuario> getAllUsuarios() {
        return controleUsuario.getAll();
    }

    public boolean atualizarUsuario(Usuario usuario) {
        return controleUsuario.update(usuario);
    }

    public boolean deletarUsuario(Long id) {
        return controleUsuario.delete(id);
    }

    public List<Usuario> getUsuarioByFuncao(FuncaoUsuario tipo) {
        return controleUsuario.getByFuncao(tipo);
    }
}
