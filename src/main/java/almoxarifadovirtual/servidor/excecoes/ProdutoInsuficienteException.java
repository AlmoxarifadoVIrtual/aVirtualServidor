package almoxarifadovirtual.servidor.excecoes;

public class ProdutoInsuficienteException extends RuntimeException {

  public ProdutoInsuficienteException() {
    super("Não existe quantidade de produtos suficientes no sistema para atender a requisição!");
  }
}
