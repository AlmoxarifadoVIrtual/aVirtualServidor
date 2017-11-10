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
        this.nome = nome;
        this.funcao = tipoUsuario;
    }

    public Usuario() {
        this.nome = "ROOT";
        this.funcao = FuncaoUsuario.ADMINISTRADOR;
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

}
