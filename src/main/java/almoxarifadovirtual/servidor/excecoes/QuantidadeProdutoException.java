package almoxarifadovirtual.servidor.excecoes;

public class QuantidadeProdutoException extends RuntimeException {

  public QuantidadeProdutoException() {
    super("A quantidade de produtos inserida não pode ser negativa");
  }
}
