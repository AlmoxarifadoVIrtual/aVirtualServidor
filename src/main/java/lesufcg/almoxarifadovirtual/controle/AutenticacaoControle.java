package lesufcg.almoxarifadovirtual.controle;

import lesufcg.almoxarifadovirtual.modelo.autenticacao.Token;
import lesufcg.almoxarifadovirtual.repositorio.RepositorioToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoControle {

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