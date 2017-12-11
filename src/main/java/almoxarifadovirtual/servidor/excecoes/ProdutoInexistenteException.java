package almoxarifadovirtual.servidor.excecoes;

public class ProdutoInexistenteException extends RuntimeException {

  public ProdutoInexistenteException() {
    super("Produto não cadastrado no sistema!");
  }

}
