package lesufcg.almoxarifadovirtual.repositorio;

import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    /**
     * Método que retorna uma lista de clientes fazendo a busca pelo nome passado como parâmetro.
     *
     * @param name
     * @return lista de clientes
     */
    Usuario findByNome(@Param("nome") String name);

    /**
     * Método que retorna o cliente com apenas seu nome fazendo a busca com o id passado como parâmetro.
     *
     * @param funcao
     * @return lista de usuarios com a funcao passada como parametro.
     */
    @Query("SELECT usr FROM Usuario usr where usr.funcao = :funcao")
    List<Usuario> findByFuncao(String funcao);

}
