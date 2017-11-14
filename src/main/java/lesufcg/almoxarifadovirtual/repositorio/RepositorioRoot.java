package lesufcg.almoxarifadovirtual.repositorio;


import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Alessandro Fook on 23/02/2017.
 */
@Component
public class RepositorioRoot implements ApplicationRunner {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public RepositorioRoot(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        Usuario root = new Usuario();
        Usuario usuario = repositorioUsuario.findUsuarioByNome(root.getNome());
        if(!root.equals(usuario)) {
            repositorioUsuario.save(root);
        }
    }
}