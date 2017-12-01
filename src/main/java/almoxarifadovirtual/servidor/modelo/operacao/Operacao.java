package almoxarifadovirtual.servidor.modelo.operacao;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private Produto produto;


  public Operacao() {}

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

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }
}
