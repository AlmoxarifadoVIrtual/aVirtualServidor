package almoxarifadovirtual.servidor.modelo.usuario;

import java.util.Arrays;

public enum FuncaoUsuario {

  ADMINISTRADOR("ADMINISTRADOR"), ALMOXARIFE("ALMOXARIFE"), PRESTADOR("PRESTADOR");

  private String valor;

  private FuncaoUsuario(String valor) {
    this.valor = valor;
  }

  public static FuncaoUsuario selecionarFuncao(String valor) {

    for (FuncaoUsuario funcaoUsuario : values()) {
      if (funcaoUsuario.valor.equalsIgnoreCase(valor)) {
        return funcaoUsuario;
      }
    }

    throw new IllegalArgumentException(
        "Funcao nao cadastrada no sistema " + valor + ", as funções válidas são " + Arrays
            .toString(values()));
  }
}
