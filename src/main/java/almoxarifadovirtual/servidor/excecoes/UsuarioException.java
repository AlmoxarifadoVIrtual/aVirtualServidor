package almoxarifadovirtual.servidor.excecoes;

public class UsuarioException extends RuntimeException {
  public UsuarioException() {
    super("Usuário não cadastrado no sistema!");
  }
}
