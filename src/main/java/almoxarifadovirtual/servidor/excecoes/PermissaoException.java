package almoxarifadovirtual.servidor.excecoes;

public class PermissaoException extends RuntimeException {

  public PermissaoException() {
    super("O usuário não permissão para executar a operação desejada!");
  }
}
