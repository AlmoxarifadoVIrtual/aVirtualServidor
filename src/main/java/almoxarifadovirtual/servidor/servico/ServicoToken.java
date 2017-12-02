package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.excecoes.PermissaoException;
import almoxarifadovirtual.servidor.excecoes.TokenException;
import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.repositorio.RepositorioToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoToken {

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
  public Token validarToken(String chave) {
    Token token = getTokenByChave(chave);

    if (token != null && token.validarToken()) {
      return token;
    } else {
      deletarToken(token);
      return null;
    }
  }

  /**
   * Método que verifica se o id informado corresponde ao usuário da chave.
   *
   * @param chave - Código de acesso do usuário.
   * @param id - Identificação do usuário do tipo Long.
   * @return - True se o id estiver cadastrado no token correspondente a chave.
   */
  public boolean validarUsuarioId(String chave, Long id) {

    Token token = validarToken(chave);

    if (token != null && token.getUsuarioId().equals(id)) {
      return true;
    } else {
      return false;
    }
  }
}