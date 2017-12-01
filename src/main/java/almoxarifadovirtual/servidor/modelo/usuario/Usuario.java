package almoxarifadovirtual.servidor.modelo.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  @NotEmpty(message = "O nome do usuário não pode ser vazio.")
  private String nome;

  @Column(nullable = false)
  private FuncaoUsuario funcao;

  /**
   * Construtor do objeto que representa um usuário do sistema.
   *
   * @param nome - String que representa o nome do usuário.
   * @param tipoUsuario - Enum que representa o tipo de usuário e define seu nível de acesso no
   *                      sistema.
   */
  public Usuario(String nome, FuncaoUsuario tipoUsuario) {
    this.nome = nome;
    this.funcao = tipoUsuario;
  }

  public Usuario() {
  }

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

  public FuncaoUsuario getFuncao() {
    return funcao;
  }

  public void setFuncao(FuncaoUsuario tipoUsuario) {
    this.funcao = tipoUsuario;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Usuario usuario = (Usuario) o;

    if (!getNome().equals(usuario.getNome())) {
      return false;
    }

    return getFuncao() == usuario.getFuncao();
  }

  @Override
  public int hashCode() {
    int result = getNome().hashCode();
    result = 31 * result + getFuncao().hashCode();
    return result;
  }
}
