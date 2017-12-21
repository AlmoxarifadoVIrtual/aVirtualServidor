package almoxarifadovirtual.servidor.modelo.operacao;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import java.util.Arrays;

public enum TipoDeOperacao {

  CADASTRO("cadastro"), RETIRADA("retirada"), DEPOSITO("deposito");

  private String tipo;

  private TipoDeOperacao(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Método que retorna o enum correspondente a tipo contida no parâmetro.
   * @param tipo - String que represente o nome da função a ser recuperada.
   * @return Um Enum da função correspondente ao parâmetro.
   */
  public static TipoDeOperacao selecionarTipo(String tipo) {

    for (TipoDeOperacao tipoDeOperacao : values()) {
      if (tipoDeOperacao.tipo.equalsIgnoreCase(tipo)) {
        return tipoDeOperacao;
      }
    }

    throw new IllegalArgumentException(
        "Funcao nao cadastrada no sistema " + tipo + ", as funções válidas são " + Arrays
            .toString(values()));
  }
}
