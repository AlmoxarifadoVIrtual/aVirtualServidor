package almoxarifadovirtual.servidor.modelo.autenticacao;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.validation.annotation.Validated;

@Entity(name = "Token")
@Table(name = "tb_token")
@Validated
public class Token {

  private static final int UMA_HORA = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String chave;

  @Column(nullable = false)
  private String expirationDate;

  @Column(unique = true, nullable = false)
  private Long usuarioId;

  /**
   * Construtor do objeto Token que é utilizado para validar uma seção de usuário após o login.
   *
   * @param usuarioId - Id do usuário que solicitou a conexão ao sistema.
   */
  public Token(Long usuarioId) {
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

  /**
   * Método que realiza a validação do token considerando sua data de validade.
   *
   * @return True caso a dava de validade seja menor que a data local atual, e False caso contrário.
   */
  public boolean validarToken() {

    String now = LocalDateTime.now().toString();
    return this.expirationDate.compareTo(now) >= 0;
  }
}
