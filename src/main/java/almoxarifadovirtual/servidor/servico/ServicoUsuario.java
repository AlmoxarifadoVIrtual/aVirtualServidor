package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorio;

    public void setRepositorio(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario create(Usuario usuario) {
        System.out.println(usuario + "estah sendo criado");
        return repositorio.save(usuario);
    }

    public Usuario get(Long id) {
        return repositorio.findOne(id);
    }

    public Usuario get(String nome) {
        return repositorio.findByNome(nome);
    }

    public List<Usuario> get(FuncaoUsuario tipo) {
        return repositorio.findByFuncao(tipo);
    }


    public List<Usuario> getAll() {
        return repositorio.findAll();
    }

    public boolean update(Usuario usuario) {

        System.out.println(usuario + "estah sendo atualizado");

        if (repositorio.exists(usuario.getId())) {
            repositorio.save(usuario);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (repositorio.exists(id)) {
            repositorio.delete(id);
            return true;
        }
        return false;
    }

}