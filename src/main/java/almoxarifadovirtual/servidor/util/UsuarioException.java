package almoxarifadovirtual.servidor.util;

public class UsuarioException extends RuntimeException {
  public UsuarioException() {
    super("Usuário não cadastrado no sistema!");
  }
}
