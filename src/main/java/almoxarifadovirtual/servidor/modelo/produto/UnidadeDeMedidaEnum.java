package almoxarifadovirtual.servidor.modelo.produto;
import java.util.Arrays;

public enum UnidadeDeMedidaEnum {

  UNIDADE("UNIDADE"), KILOGRAMA("KILOGRAMA"), METRO("METRO"), LITRO("LITRO");

  private String valor;

  private UnidadeDeMedidaEnum(String valor) {
    this.valor = valor;
  }

  /**
   * Método que retorna o enum correspondente a unidade de medida.
   * @param funcao - String que represente o nome da função a ser recuperada.
   * @return Um Enum da função correspondente ao parâmetro.
   */
  public static UnidadeDeMedidaEnum selecionarUnidade(String funcao) {

    for (UnidadeDeMedidaEnum unidade : values()) {
      if (unidade.valor.equalsIgnoreCase(funcao)) {
        return unidade;
      }
    }

    throw new IllegalArgumentException(
        "Funcao nao cadastrada no sistema " + funcao + ", as funções válidas são " + Arrays
            .toString(values()));
  }
}
