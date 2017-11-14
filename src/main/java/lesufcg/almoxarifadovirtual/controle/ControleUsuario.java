package lesufcg.almoxarifadovirtual.controle;

import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import lesufcg.almoxarifadovirtual.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleUsuario {

    @Autowired
    private RepositorioUsuario repositorio;

    public void setRepositorio(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario create(Usuario usuario) {
        validateId(usuario.getId());
        System.out.println(usuario + "estah sendo criado");
        return repositorio.save(usuario);
    }

    public Usuario get(Long id) {
        validateId(id);
        return repositorio.findOne(id);
    }

    public List<Usuario> getAll() {

        List<Usuario> usuarios = repositorio.findAll();
        Usuario root = new Usuario();
        usuarios.remove(root);

        return usuarios;
    }

    public boolean update(Usuario usuario) {

        validateId(usuario.getId());

        System.out.println(usuario + "estah sendo atualizado");

        if (repositorio.exists(usuario.getId())) {
            repositorio.save(usuario);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        validateId(id);
        if (repositorio.exists(id)) {
            repositorio.delete(id);
            return true;
        }
        return false;
    }

    public List<Usuario> getByFuncao(FuncaoUsuario tipo) {
        return repositorio.findByFuncao(tipo);
    }

    public void validateId(Long id) {
        if(id == null) return;

        if (id == 1) {
            throw new RuntimeException("Invalid id: " + id);
        }

    }
}