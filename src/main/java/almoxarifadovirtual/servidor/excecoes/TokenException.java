package almoxarifadovirtual.servidor.excecoes;

public class TokenException extends RuntimeException {

  public TokenException() {
    super("Token inválido!");
  }

  public TokenException(String data) {
    super("Token ultrapassou o limite de tempo, pois foi emitido em " + data);
  }
}
