package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeProduto extends JpaRepository<Produto, Long> {

  Produto findById(Long id);

  List<Produto> findByNome(String nome);

  List<Produto> findByMarca(String marca);

  Produto findByReferencia(String referencia);

  List<Produto> findProdutosByDescricaoIsContaining(String descricao);

}
