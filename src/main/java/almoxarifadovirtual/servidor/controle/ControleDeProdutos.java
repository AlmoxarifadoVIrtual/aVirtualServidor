package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.excecoes.ProdutoCadastradoException;
import almoxarifadovirtual.servidor.excecoes.ProdutoInexistenteException;
import almoxarifadovirtual.servidor.excecoes.ProdutoInsuficienteException;
import almoxarifadovirtual.servidor.excecoes.QuantidadeProdutoException;
import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.operacao.TipoDeOperacao;
import almoxarifadovirtual.servidor.modelo.produto.Produto;
import almoxarifadovirtual.servidor.servico.ServicoDeOperacao;
import almoxarifadovirtual.servidor.servico.ServicoDeProduto;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  ServicoDeOperacao servicoDeOperacao;

  @Autowired
  ControleDeAutenticacao controleDeAutenticacao;

  /**
   * Método para inserir um novo produto no estoque, ou alterar sua quantidade.
   * @param produto - produto que será inserido ou modificado.
   * @param chave - String de autenticação de autorização da operação.
   * @return - O objeto produto atualizado conforme se encontra no Banco de Dados.
   */
  @PostMapping
  @ResponseBody
  public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);
    Produto produtoCadastrado = servicoDeProduto.encontrarProduto(produto.getId());

    if (produtoCadastrado == null) {
      Operacao cadastro = new Operacao(TipoDeOperacao.CADASTRO, LocalDateTime.now().toString(),
          Arrays.asList(produto), controleDeAutenticacao.getUsuarioId(chave));
      servicoDeOperacao.save(cadastro);

      return Utils.generateResponse(servicoDeProduto.salvarProduto(produto));

    } else if (produtoCadastrado.equals(produto)) {
      double armazenado = produtoCadastrado.getQuantidade();
      double inserido = produto.getQuantidade();

      if (inserido > 0) {
        produtoCadastrado.setQuantidade(armazenado + inserido);
        Operacao deposito = new Operacao(TipoDeOperacao.DEPOSITO, LocalDateTime.now().toString(),
            Arrays.asList(produto), controleDeAutenticacao.getUsuarioId(chave));
        servicoDeOperacao.save(deposito);
        return Utils.generateResponse(servicoDeProduto.salvarProduto(produtoCadastrado));

      } else {
        throw new QuantidadeProdutoException();
      }

    } else {
      throw new ProdutoCadastradoException();
    }
  }

  /**
   * Método que retira produtos do estoque.
   * @param id - Identificação do produto.
   * @param quantidadeRetirada - Quantidade a ser removida.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   */
  @DeleteMapping("/{id}/{quantidadeRetirada}")
  @ResponseBody
  public void retirarProduto(@PathVariable("id") Long id,
      @PathVariable("quantidadeRetirada") double quantidadeRetirada, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    Produto produto = servicoDeProduto.encontrarProduto(id);
    validarProduto(produto);

    double qntDisponivel = produto.getQuantidade();

    if (qntDisponivel >= quantidadeRetirada) {

      produto.setQuantidade(qntDisponivel - quantidadeRetirada);
      servicoDeProduto.salvarProduto(produto);

      Operacao retirada = new Operacao(TipoDeOperacao.RETIRADA, LocalDateTime.now().toString(),
          Arrays.asList(produto), controleDeAutenticacao.getUsuarioId(chave));
      servicoDeOperacao.save(retirada);

    } else {
      throw new ProdutoInsuficienteException();
    }
  }

  private void validarProduto(Produto produto) {
    if (produto == null) {
      throw new ProdutoInexistenteException();
    }
  }

  /**
   * Método que recupera um produto.
   * @param id - Id do produto
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Objeto do tipo produto correspondente a Id.
   */
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Produto> getProduto(@PathVariable("id") Long id, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    Produto produto = servicoDeProduto.encontrarProduto(id);
    validarProduto(produto);

    return Utils.generateResponse(produto);
  }

  /**
   * Método que lista todos os produtos cadastrados no estoque.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Lista com todos os produtos cadastrados no estoque.
   */
  @GetMapping("/listar")
  @ResponseBody
  public ResponseEntity<List<Produto>> listarProdutos(@RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    List<Produto> produtos = servicoDeProduto.listarProdutos();
    validarProdutos(produtos);

    return Utils.generateResponse(produtos);
  }

  /**
   * Método que recupera uma lista de produtos filtrada pelo nome.
   * @param nomeDoProduto - String contendo o nome do produto a ser pesquisado.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Uma lista contendo todos os produtos com o nome idêntico ao do parâmetro.
   */
  @GetMapping("/listar/nome/{nomeDoProduto}")
  @ResponseBody
  public ResponseEntity<List<Produto>> encontrarProdutosPeloNome(
      @PathVariable("nomeDoProduto") String nomeDoProduto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    List<Produto> produtos = servicoDeProduto.encontrarProdutoPeloNome(nomeDoProduto);
    validarProdutos(produtos);

    return Utils.generateResponse(produtos);
  }

  /**
   * Método que recupera uma lista de produtos pela marca.
   * @param marcaDoProduto String contendo a marca do produto a ser pesquisado.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Uma lista contendo todos os produtos com a  marca idêntica ao do parâmetro.
   */
  @GetMapping("/listar/marca/{marcaDoProduto}")
  @ResponseBody
  public ResponseEntity<List<Produto>> encontrarProdutosPelaMarca(
      @PathVariable("marcaDoProduto") String marcaDoProduto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    List<Produto> produtos = servicoDeProduto.encontrarProdutoPelaMarca(marcaDoProduto);
    validarProdutos(produtos);

    return Utils.generateResponse(produtos);
  }

  /**
   * Método que recupera um produto a partir da referência.
   * @param referenciaDoProduto - String contendo a referência a ser pesquisada.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Um produto com a referência igual ao parâmetro.
   */
  @GetMapping("/listar/referencia/{referenciaDoProduto}")
  @ResponseBody
  public ResponseEntity<Produto> encontrarProdutoPelaReferencia(
      @PathVariable("referenciaDoProduto") String referenciaDoProduto,
      @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    Produto produto = servicoDeProduto.encontrarProdutoPelaReferencia(referenciaDoProduto);
    validarProduto(produto);

    return Utils.generateResponse(produto);
  }

  /**
   * Método que recupera uma lista de produtos que contém a descrição.
   * @param descricaoDoProduto String contendo a descrição do produto a ser pesquisada.
   * @param chave - String que identifica o usuaŕio que está solicitando a operação.
   * @return Uma lista contendo todos os produtos que contém a descrição do parâmetro.
   */
  @GetMapping("/listar/descricao/{descricaoDoProduto}")
  @ResponseBody
  public ResponseEntity<List<Produto>> findProdutosByDescricaoIsContaining(
      @PathVariable("descricaoDoProduto") String descricaoDoProduto, @RequestHeader String chave) {

    controleDeAutenticacao.validarAlmoxarifeOuAdmin(chave);

    List<Produto> produtos = servicoDeProduto
        .findProdutosByDescricaoIsContaining(descricaoDoProduto);
    validarProdutos(produtos);

    return Utils.generateResponse(produtos);
  }

  private void validarProdutos(List<Produto> produtos) {
    if (produtos.size() == 0) {
      throw new ProdutoInexistenteException();
    }
  }

  // Métodos relativos ao registro das operações de inserção e retirada de produtos

  @GetMapping("/operacao")
  @ResponseBody
  public ResponseEntity<List<Operacao>> listarOperacoes(@RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    return Utils.generateResponse(servicoDeOperacao.findAll());
  }

  @GetMapping("/operacao/tipo/{tipoOperacao}")
  @ResponseBody
  public ResponseEntity<List<Operacao>> listarOperacoesPorTipo(@PathVariable("tipoOperacao") String tipoOperacao,
      @RequestHeader String chave) {

    controleDeAutenticacao.validarAdmin(chave);
    return Utils.generateResponse(servicoDeOperacao.findByTipoDeOperacao(tipoOperacao));
  }

  @GetMapping("/operacao/usuario/{usuarioId}")
  @ResponseBody
  public ResponseEntity<List<Operacao>> listarOperacoesPorUsuarioId(@PathVariable("usuarioId") Long usuarioId,
      @RequestHeader String chave) {
    controleDeAutenticacao.validarAdmin(chave);
    return Utils.generateResponse(servicoDeOperacao.findByUsuarioId(usuarioId));
  }

}
