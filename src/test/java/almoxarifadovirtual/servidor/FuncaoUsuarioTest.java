package almoxarifadovirtual.servidor;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FuncaoUsuarioTest {

    @Test(expected = IllegalArgumentException.class)
    public void selecionarFuncaoExceptionTest() {
        FuncaoUsuario.selecionarFuncao("Frentista");
    }

    @Test
    public void SelecionarFuncaoAlmoxarifeTest() {
        FuncaoUsuario funcao = FuncaoUsuario.selecionarFuncao("almoxarife");
        assertThat(funcao).isEqualTo(FuncaoUsuario.ALMOXARIFE);
    }
    @Test
    public void SelecionarFuncaoAdministradorTest() {
        FuncaoUsuario funcao = FuncaoUsuario.selecionarFuncao("Administrador");
        assertThat(funcao).isEqualTo(FuncaoUsuario.ADMINISTRADOR);
    }

    @Test
    public void SelecionarFuncaoPrestadorTest() {
        FuncaoUsuario funcao = FuncaoUsuario.selecionarFuncao("Prestador");
        assertThat(funcao).isEqualTo(FuncaoUsuario.PRESTADOR);
    }
}
