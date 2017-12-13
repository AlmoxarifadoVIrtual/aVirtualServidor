package almoxarifadovirtual.servidor.excecoes;

public class ProdutoCadastradoException extends RuntimeException {

  public ProdutoCadastradoException () {
    super("Produto similar já cadastrado no sistema, operação inválida!");
  }
}
