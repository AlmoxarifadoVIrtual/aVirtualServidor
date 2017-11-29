package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.repositorio.RepositorioUsuario;
import almoxarifadovirtual.servidor.excecoes.PermissaoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicoUsuario {

  @Autowired
  private RepositorioUsuario repositorio;

  public void setRepositorio(RepositorioUsuario repositorio) {
    this.repositorio = repositorio;
  }

  public Usuario create(Usuario usuario) {
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

  /**
   * Método que atualiza dos dados de um usuário.
   * @param usuario - Objeto do tipo Usuario com as informações atualizadas.
   * @return Um boolean True caso o usuário exista, e false caso contrário.
   */
  public boolean update(Usuario usuario) {

    if (repositorio.exists(usuario.getId())) {
      repositorio.save(usuario);
      return true;
    }
    return false;
  }

  /**
   * Método que remove um usuário do sistema.
   * @param id - Valor que identifica o usuário do tipo Long.
   * @return True se o usuário existir, ou False caso contrário.
   */
  public boolean delete(Long id) {
    if (repositorio.exists(id)) {
      repositorio.delete(id);
      return true;
    }
    return false;
  }

  public boolean validarAdmin(Long id) {
    Usuario usuario = get(id);
    if (usuario.getFuncao() == FuncaoUsuario.ADMINISTRADOR) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ADMINISTRADOR);
    }
  }

  public boolean validarPrestador(Long id) {
    Usuario usuario = get(id);

    if (usuario.getFuncao() == FuncaoUsuario.PRESTADOR) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.PRESTADOR);
    }
  }

  public boolean validarAlmoxarife(Long id) {
    Usuario usuario = get(id);
    if (usuario.getFuncao() == FuncaoUsuario.ALMOXARIFE) {
      return true;
    } else {
      throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ALMOXARIFE);
    }
  }

}