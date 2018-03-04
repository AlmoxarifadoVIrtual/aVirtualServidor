package almoxarifadovirtual.servidor.modelo.reserva;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.websocket.ClientEndpoint;

@Entity(name = "Reserva")
@Table(name = "tb_reserva")
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(name = "entregue")
  private boolean entregue;

  @Column(name = "usuarioId")
  private Long usuarioId;

  @Column(name = "servico")
  private String servico;

  @Column(name = "data")
  private String data;

  public Reserva(){}

  @OneToMany
  private List<Produto> carrinho;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isEntregue() {
    return entregue;
  }

  public void setEntregue(boolean entregue) {
    this.entregue = entregue;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public String getServico() {
    return servico;
  }

  public void setServico(String servico) {
    this.servico = servico;
  }

  public List<Produto> getCarrinho() {
    return carrinho;
  }

  public void setCarrinho(List<Produto> carrinho) {
    this.carrinho = carrinho;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

}
