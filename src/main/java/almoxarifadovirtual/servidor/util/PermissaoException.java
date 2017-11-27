package almoxarifadovirtual.servidor.util;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;

public class PermissaoException extends RuntimeException {

  public PermissaoException(FuncaoUsuario funcaoRequerente, FuncaoUsuario funcaoAutorizada) {
    super("A funcao do solicitante é " + funcaoRequerente.toString() + ", sendo diferente de "
          + funcaoAutorizada.toString() + " que é a função autorizada para esta operação!");
  }

  public PermissaoException() {
    super("O usuário não tem acesso a essa informação!");
  }
}
