package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import almoxarifadovirtual.servidor.repositorio.RepositorioDeProduto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeProduto {

  @Autowired
  RepositorioDeProduto repositorioDeProduto;


  public ServicoDeProduto() {
  }

  public Produto encontrarProdutoPeloId(Long id) {
    return repositorioDeProduto.findById(id);
  }

  public List<Produto> encontrarProdutoPeloNome(String nome) {
    return repositorioDeProduto.findByNome(nome);
  }

  public List<Produto> encontrarProdutoPelaMarca(String marca) {
    return repositorioDeProduto.findByMarca(marca);
  }

  public Produto encontrarProdutoPelaReferencia(
      String referencia) {
    return repositorioDeProduto.findByReferencia(referencia);
  }

  public List<Produto> findProdutosByDescricaoIsContaining(
      String descricao) {
    return repositorioDeProduto.findProdutosByDescricaoIsContaining(descricao);
  }

  public List<Produto> listarProdutos() {
    return repositorioDeProduto.findAll();
  }

  public Produto salvarProduto(Produto produto) {
    return repositorioDeProduto.save(produto);
  }

  public Produto encontrarProduto(Long idProduto) {
    return repositorioDeProduto.findOne(idProduto);
  }

  public void removerProduto(Long idProduto) {
    repositorioDeProduto.delete(idProduto);
  }
}
