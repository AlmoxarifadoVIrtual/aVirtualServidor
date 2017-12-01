package almoxarifadovirtual.servidor.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "Produto")
@Table(name = "tb_produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  @NotEmpty(message = "O nome do produto não pode ser vazio.")
  private String nome;

  @Column(nullable = false)
  @NotEmpty(message = "A marca do produto não pode ser vazia.")
  private String marca;

  @Column(nullable = false)
  @NotEmpty(message = "A referencia do produto não pode ser vazia.")
  private String referencia;

  @Column(nullable = false)
  @NotEmpty(message = "A cor do produto não pode ser vazia.")
  private String cor;

  @Column(nullable = false)
  @NotEmpty(message = "A descriçao do produto não pode ser vazio.")
  private String descricao;

  @Column(nullable = false)
  @DecimalMin(value = "0.0", message = "Não existe quantidade negativa.")
  private double quantidade;


  public Produto() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public double getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(double quantidade) {
    this.quantidade = quantidade;
  }
}
