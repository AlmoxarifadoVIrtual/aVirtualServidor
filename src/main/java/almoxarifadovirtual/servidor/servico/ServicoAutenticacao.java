package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.repositorio.RepositorioToken;
import almoxarifadovirtual.servidor.util.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoAutenticacao {

  @Autowired
  RepositorioToken repository;

  public void deletarToken(Token token) {
    repository.delete(token);
  }

  public Token getTokenByChave(String chave) {
    return repository.findByChave(chave);
  }

  public Token getTokenByUsuarioId(Long usuarioId) {
    return repository.findByUsuarioId(usuarioId);
  }

  public Token gerarToken(Long usuarioId) {
    return repository.save(new Token(usuarioId));
  }

  /**
   * Método que realiza a validação do token considerando sua data de validade.
   *
   * @return True caso a dava de validade seja menor que a data local atual, e False caso contrário.
   */
  public boolean validarToken(String chave) {
    Token token = getTokenByChave(chave);

    if (token == null) {
      throw new TokenException();
    } else if (token.validarToken()) {
      return true;
    } else {
      deletarToken(token);
      throw new TokenException(token.getExpirationDate());
    }
  }
}