package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeUsuario extends JpaRepository<Usuario, Long> {


  Usuario findByNome(String nome);

  /**
   * Método que retorna uma lista de usuarios a partir da funcao passada como parâmetro.
   * @return lista de usuarios com a funcao passada como parametro.
   */
  @Query("SELECT usr FROM Usuario usr where usr.funcao = :funcao")
  List<Usuario> findByFuncao(@Param("funcao") FuncaoUsuario funcao);
}
