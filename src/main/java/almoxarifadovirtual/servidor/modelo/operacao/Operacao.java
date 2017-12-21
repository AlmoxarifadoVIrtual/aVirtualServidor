package almoxarifadovirtual.servidor.modelo.operacao;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Operacao")
@Table(name = "tb_operacao")
public class Operacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private TipoDeOperacao tipoDeOperacao;

  @Column(nullable = false)
  private String dataDaOperacao;

  @OneToMany
  private List<Produto> produtos;

  @Column(nullable = false)
  private Long usuarioId;

  public Operacao() {}

  /**
   * Contrutor de um objeto do tipo Operacao.
   * @param tipoDeOperacao - Tipo da operação que está sendo criada.
   * @param dataDaOperacao - Data em que a operação foi realizada.
   * @param produtos - Produtos que foram modificados na operação, com a quantidade modificada.
   * @param usuarioId - Id do usuário que realizou a operação.
   */
  public Operacao(TipoDeOperacao tipoDeOperacao, String dataDaOperacao,
      List<Produto> produtos, Long usuarioId) {
    this.tipoDeOperacao = tipoDeOperacao;
    this.dataDaOperacao = dataDaOperacao;
    this.produtos = produtos;
    this.usuarioId = usuarioId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TipoDeOperacao getTipoDeOperacao() {
    return tipoDeOperacao;
  }

  public void setTipoDeOperacao(TipoDeOperacao tipoDeOperacao) {
    this.tipoDeOperacao = tipoDeOperacao;
  }

  public String getDataDaOperacao() {
    return dataDaOperacao;
  }

  public void setDataDaOperacao(String dataDaOperacao) {
    this.dataDaOperacao = dataDaOperacao;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }
}
