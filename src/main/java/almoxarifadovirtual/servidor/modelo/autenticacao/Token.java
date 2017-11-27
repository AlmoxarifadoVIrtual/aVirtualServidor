package almoxarifadovirtual.servidor.modelo.autenticacao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Token")
@Table(name = "tb_token")
public class Token {

    private final int UMA_HORA = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String chave;

    @Column
    private String expirationDate;

    @Column(unique = true)
    private Long usuarioId;

    public Token(Long usuarioId){
        setExpirationDate(LocalDateTime.now().plusHours(UMA_HORA));
        setChave(UUID.randomUUID().toString());
        setUsuarioId(usuarioId);
    }

    public Token() {
    }

    public String getChave() {
        return chave;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate.toString();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean validarToken(){ return this.expirationDate.compareTo(LocalDateTime.now().toString()) < 0;}
}