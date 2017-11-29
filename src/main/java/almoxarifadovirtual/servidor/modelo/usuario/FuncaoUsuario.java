package almoxarifadovirtual.servidor.modelo.usuario;

import java.util.Arrays;

public enum FuncaoUsuario {

  ADMINISTRADOR("ADMINISTRADOR"), ALMOXARIFE("ALMOXARIFE"), PRESTADOR("PRESTADOR");

  private String valor;

  private FuncaoUsuario(String valor) {
    this.valor = valor;
  }

  /**
   * Método que retorna o enum correspondente a funcao contida no parâmetro.
   * @param funcao - String que represente o nome da função a ser recuperada.
   * @return Um Enum da função correspondente ao parâmetro.
   */
  public static FuncaoUsuario selecionarFuncao(String funcao) {

    for (FuncaoUsuario funcaoUsuario : values()) {
      if (funcaoUsuario.valor.equalsIgnoreCase(funcao)) {
        return funcaoUsuario;
      }
    }

    throw new IllegalArgumentException(
        "Funcao nao cadastrada no sistema " + funcao + ", as funções válidas são " + Arrays
            .toString(values()));
  }
}
