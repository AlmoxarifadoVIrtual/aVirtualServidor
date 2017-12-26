package almoxarifadovirtual.servidor;

import almoxarifadovirtual.servidor.modelo.produto.Produto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoTest {
    private Produto caboDeVassoura;

    @Before
    public void configureProdutoTest() {
        caboDeVassoura = new Produto();
    }

    @After
    public void limparProdutoTest() {
        caboDeVassoura = null;
    }

    @Test
    public void GetESetAtributosProdutoTest() {
        caboDeVassoura.setNome("Cabo de vassoura");
        caboDeVassoura.setDescricao("Um cabo bom danado.");
        caboDeVassoura.setCor("Preto");
        caboDeVassoura.setMarca("Cabos moura");
        caboDeVassoura.setQuantidade(15);
        caboDeVassoura.setReferencia("3D4G");

        assertThat(caboDeVassoura.getId()).isNull();

        caboDeVassoura.setId((long) 666);

        assertThat(caboDeVassoura.getId()).isEqualTo(666);
        assertThat(caboDeVassoura.getNome()).isEqualTo("Cabo de vassoura");
        assertThat(caboDeVassoura.getDescricao()).isEqualTo("Um cabo bom danado.");
        assertThat(caboDeVassoura.getCor()).isEqualTo("Preto");
        assertThat(caboDeVassoura.getMarca()).isEqualTo("Cabos moura");
        assertThat(caboDeVassoura.getQuantidade()).isEqualTo(15);
        assertThat(caboDeVassoura.getReferencia()).isEqualTo("3D4G");
    }

    @Test
    public void EqualsProdutoTest() {
        caboDeVassoura.setNome("Cabo de vassoura");
        caboDeVassoura.setDescricao("Um cabo bom danado.");
        caboDeVassoura.setCor("Preto");
        caboDeVassoura.setMarca("Cabos moura");
        caboDeVassoura.setQuantidade(15);
        caboDeVassoura.setReferencia("3D4G");
        caboDeVassoura.setId((long) 666);

        Produto outroCaboDeVassoura = new Produto();
        outroCaboDeVassoura.setNome("Cabo de vassoura");
        outroCaboDeVassoura.setDescricao("Um cabo bom danado.");
        outroCaboDeVassoura.setCor("Preto");
        outroCaboDeVassoura.setMarca("Cabos moura");
        outroCaboDeVassoura.setQuantidade(15);
        outroCaboDeVassoura.setReferencia("3D4G");
        outroCaboDeVassoura.setId((long) 666);

        assertThat(caboDeVassoura).isEqualTo(outroCaboDeVassoura);
    }

    @Test
    public void NotEqualsProdutoTest() {
        Produto outroCaboDeVassoura = new Produto();
        outroCaboDeVassoura.setNome("Cabo de vassoura");
        outroCaboDeVassoura.setDescricao("Um cabo bom danado.");
        outroCaboDeVassoura.setCor("Preto");

        assertThat(caboDeVassoura).isNotEqualTo(outroCaboDeVassoura);
    }
}
