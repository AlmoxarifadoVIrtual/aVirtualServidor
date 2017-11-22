package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.repositorio.RepositorioToken;
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

    public Token getTokenByUsuarioId(Long usuarioId){return repository.findByUsuarioId(usuarioId);}

    public Token gerarToken(Long usuarioId) {
        return repository.save(new Token(usuarioId));
    }

}