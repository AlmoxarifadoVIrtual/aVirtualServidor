package almoxarifadovirtual.servidor.modelo.operacao;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

  @Column(nullable = false)
  private List<Produto> produtos;

  @Column(nullable = false)
  @ManyToOne
  private Usuario usuario;

  public Operacao() {}

  public Operacao(TipoDeOperacao tipoDeOperacao, String dataDaOperacao,
      List<Produto> produtos, Usuario usuario) {
    this.tipoDeOperacao = tipoDeOperacao;
    this.dataDaOperacao = dataDaOperacao;
    this.produtos = produtos;
    this.usuario = usuario;
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

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
