package lesufcg.almoxarifadovirtual.modelo.usuario;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    private FuncaoUsuario funcao;

    public Usuario(String nome, FuncaoUsuario tipoUsuario) {
        if(tipoUsuario != FuncaoUsuario.ROOT) {
            this.nome = nome;
            this.funcao = tipoUsuario;
        } else {
            throw new RuntimeException("Tipo inválido: " + FuncaoUsuario.ROOT.toString());
        }
    }

    public Usuario() {
        this.nome = "root";
        this.funcao = FuncaoUsuario.ROOT;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!getNome().equals(usuario.getNome())) return false;
        return getFuncao() == usuario.getFuncao();
    }

    @Override
    public int hashCode() {
        int result = getNome().hashCode();
        result = 31 * result + getFuncao().hashCode();
        return result;
    }
}
