package almoxarifadovirtual.servidor;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UsuarioTests {
  private Usuario usuarioBefore;

  @Before
  public void configureUsuario() {
    this.usuarioBefore = new Usuario("Cleosvaldo", FuncaoUsuario.ALMOXARIFE);
  }

  @After
  public void limparUsuario() {
    this.usuarioBefore = null;
  }

  @Test
  public void FuncaoUsuarioTest() {
    assertThat(usuarioBefore.getFuncao()).isEqualTo(FuncaoUsuario.ALMOXARIFE);
  }

  @Test
  public void SetFuncaoUsuarioTest() {
    usuarioBefore.setFuncao(FuncaoUsuario.ADMINISTRADOR);
    assertThat(usuarioBefore.getFuncao()).isEqualTo(FuncaoUsuario.ADMINISTRADOR);
  }

  @Test
  public void NomeUsuarioTest() {
    assertThat(usuarioBefore.getNome()).isNotEmpty();
    assertThat(usuarioBefore.getNome()).isEqualTo("Cleosvaldo");
  }

  @Test
  public void SetNomeUsuarioTest() {
    usuarioBefore.setNome("Matias");
    assertThat(usuarioBefore.getNome()).isEqualTo("Matias");
  }

  @Test
  public void IdUsuarioTest() {
    // No início o id do usuário é NULL.
    assertThat(usuarioBefore.getId()).isNull();
  }

  @Test
  public void SetIdUsuarioTest() {
    usuarioBefore.setId((long) 666);
    assertThat(usuarioBefore.getId()).isEqualTo(666);
  }

  @Test
  public void EqualsUsuarioTest() {
    Usuario novoCleosvaldo = new Usuario("Cleosvaldo", FuncaoUsuario.ALMOXARIFE);
    assertThat(novoCleosvaldo).isEqualTo(usuarioBefore);
  }

  @Test
  public void NotEqualsUsuarioTest() {
    Usuario novoUsuario = new Usuario("Cleosvaldo", FuncaoUsuario.ADMINISTRADOR);
    assertThat(novoUsuario).isNotEqualTo(usuarioBefore);
  }
}
