package almoxarifadovirtual.servidor.controle;

import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.produto.Produto;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ControleDeProdutos {

  @PostMapping
  @ResponseBody
  public Produto cadastrarProduto(@RequestBody Produto produto, @RequestHeader String chave) {
    return null;
  }


  @DeleteMapping("/{id}")
  @ResponseBody
  public void retirarProduto(@PathVariable("id") Long id, @RequestHeader String chave) {

  }

  @GetMapping("/{id}")
  @ResponseBody
  public Produto getProduto(@PathVariable("id") Long id, @RequestHeader String chave) {
    return null;
  }


  @GetMapping("/listar")
  @ResponseBody
  public List<Produto> listarProdutos(@RequestHeader String chave) {
    return null;
  }

  @GetMapping("/{tipoOperacao}")
  @ResponseBody
  public List<Operacao> listarCadastros(@PathVariable("tipoOperacao") String tipoOperacao,
      @RequestHeader String chave) {
    return null;
  }
}
