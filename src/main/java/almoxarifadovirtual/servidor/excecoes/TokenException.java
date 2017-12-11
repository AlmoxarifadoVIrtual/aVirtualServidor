package almoxarifadovirtual.servidor.excecoes;

public class TokenException extends RuntimeException {

  public TokenException() {
    super("Token inválido!");
  }

}
