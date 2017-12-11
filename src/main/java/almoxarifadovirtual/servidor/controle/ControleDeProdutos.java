package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.excecoes.ProdutoInexistenteException;
import almoxarifadovirtual.servidor.excecoes.ProdutoInsuficienteException;
//import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.produto.Produto;
import almoxarifadovirtual.servidor.servico.ServicoDeProduto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ControleDeProdutos {

  @Autowired
  ServicoDeProduto servicoDeProduto;

  @Autowired
  ControleDeAutenticacao controleDeAutenticacao;

  @PostMapping
  @ResponseBody
  public Produto cadastrarProduto(@RequestBody Produto produto, @RequestHeader String chave) {
    controleDeAutenticacao.validarAlmoxarife(chave);
    return servicoDeProduto.salvarProduto(produto);
  }

  @DeleteMapping("/{id}/{quantidadeRetirada}")
  @ResponseBody
  public void retirarProduto(@PathVariable("id") Long id,
      @PathVariable("quantidadeRetirada") double quantidadeRetirada, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    Produto produto = servicoDeProduto.encontrarProduto(id);
    validarProduto(produto);

    double qntDisponivel = produto.getQuantidade();

    if (qntDisponivel >= quantidadeRetirada) {

      produto.setQuantidade(qntDisponivel - quantidadeRetirada);
      servicoDeProduto.salvarProduto(produto);

    } else {
      throw new ProdutoInsuficienteException();
    }
  }

  private void validarProduto(Produto produto) {
    if (produto == null) {
      throw new ProdutoInexistenteException();
    }
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Produto getProduto(@PathVariable("id") Long id, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    Produto produto = servicoDeProduto.encontrarProduto(id);
    validarProduto(produto);

    return produto;
  }

  @GetMapping("/listar")
  @ResponseBody
  public List<Produto> listarProdutos(@RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    List<Produto> produtos = servicoDeProduto.listarProdutos();
    validarProdutos(produtos);

    return produtos;
  }

  @GetMapping("/listar/nome/{nomeDoProduto}")
  @ResponseBody
  public List<Produto> encontrarProdutosPeloNome(
      @PathVariable("nomeDoProduto") String nomeDoProduto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    List<Produto> produtos = servicoDeProduto.encontrarProdutoPeloNome(nomeDoProduto);
    validarProdutos(produtos);

    return produtos;
  }

  @GetMapping("/listar/marca/{marcaDoProduto}")
  @ResponseBody
  public List<Produto> encontrarProdutosPelaMarca(
      @PathVariable("marcaDoProduto") String marcaDoProduto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    List<Produto> produtos = servicoDeProduto.encontrarProdutoPelaMarca(marcaDoProduto);
    validarProdutos(produtos);

    return produtos;
  }

  @GetMapping("/listar/referencia/{referenciaDoProduto}")
  @ResponseBody
  public Produto encontrarProdutoPelaReferencia(
      @PathVariable("referenciaDoProduto") String referenciaDoProduto,
      @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarife(chave);

    Produto produto = servicoDeProduto.encontrarProdutoPelaReferencia(referenciaDoProduto);
    validarProduto(produto);

    return produto;
  }

  @GetMapping("/listar/descricao/{descricaoDoProduto}")
  @ResponseBody
  public List<Produto> findProdutosByDescricaoIsContaining(@PathVariable("descricaoDoProduto") String descricaoDoProduto, @RequestHeader String chave) {
    controleDeAutenticacao.validarAlmoxarife(chave);
    List<Produto> produtos = servicoDeProduto.findProdutosByDescricaoIsContaining(descricaoDoProduto);
    validarProdutos(produtos);
    return produtos;
  }

  private void validarProdutos(List<Produto> produtos) {
    if (produtos.size() == 0) {
      throw new ProdutoInexistenteException();
    }
  }

//  @GetMapping("/{tipoOperacao}")
//  @ResponseBody
//  public List<Operacao> listarOperacoes(@PathVariable("tipoOperacao") String tipoOperacao,
//      @RequestHeader String chave) {
//    return null;
//  }
}
