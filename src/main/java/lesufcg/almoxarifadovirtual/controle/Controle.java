package lesufcg.almoxarifadovirtual.controle;

import lesufcg.almoxarifadovirtual.modelo.autenticacao.Token;
import lesufcg.almoxarifadovirtual.modelo.usuario.FuncaoUsuario;
import lesufcg.almoxarifadovirtual.modelo.usuario.Usuario;
import lesufcg.almoxarifadovirtual.util.LoginException;
import lesufcg.almoxarifadovirtual.util.PermissaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Controle {

    @Autowired
    private ControleUsuario controleUsuario;
    private AutenticacaoControle autenticacaoControle;


    public String logIn(String login, String senha){
        Usuario usuario = controleUsuario.get(login);

        if(usuario != null){
            return autenticacaoControle.gerarToken(usuario.getId()).getChave();
        }

        else throw new LoginException();

    }

    //Métodos do Usuário

    public Usuario criarUsuario(Usuario usuario, String chave) {
        if (validarToken(chave) && validarAdmin(chave)) {
            return controleUsuario.create(usuario);
        }
        return null;
    }

    public Usuario getUsuario(Long id, String chave) {

        if (validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
            return controleUsuario.get(id);
        }

        return null;
    }

    public List<Usuario> getAllUsuarios(String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return controleUsuario.getAll();
        }

        return null;
    }

    public boolean atualizarUsuario(Usuario usuario, String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return controleUsuario.update(usuario);
        }

        return false;
    }

    public boolean deletarUsuario(Long id, String chave) {

        if (validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
            return controleUsuario.delete(id);
        }

        return false;
    }

    public List<Usuario> getUsuarioByFuncao(FuncaoUsuario tipo, String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return controleUsuario.get(tipo);
        }

        return null;
    }

    //Métodos de autenticação

    public void deletarToken(Token token) {
        autenticacaoControle.deletarToken(token);
    }

    public Token getTokenByChave(String chave) {
        return autenticacaoControle.getTokenByChave(chave);
    }

    public Token getTokenByUsuarioId(Long usuarioId) {
        return autenticacaoControle.getTokenByUsuarioId(usuarioId);
    }

    public Token gerarToken(Long usuarioId) {
        return autenticacaoControle.gerarToken(usuarioId);
    }

    // Métodos auxiliares de validação

    private boolean validarToken(String chave) {

        Token token = autenticacaoControle.getTokenByChave(chave);

        if (token == null)
            return false;

        else if (token.getExpirationDate().getTime() < System.currentTimeMillis()) {
            autenticacaoControle.deletarToken(token);
            return false;

        } else
            return true;
    }

    private boolean validarAdmin(String chave) {

        Token token = autenticacaoControle.getTokenByChave(chave);
        Usuario usuario = controleUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.ADMINISTRADOR) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ADMINISTRADOR);
    }

    private boolean validarAlmoxarife(String chave) {

        Token token = autenticacaoControle.getTokenByChave(chave);
        Usuario usuario = controleUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.ALMOXARIFE) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ALMOXARIFE);
    }

    private boolean validarPrestador(String chave) {

        Token token = autenticacaoControle.getTokenByChave(chave);
        Usuario usuario = controleUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.PRESTADOR) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.PRESTADOR);
    }

    private boolean validarId(String chave, Long id) {

        Token token = autenticacaoControle.getTokenByChave(chave);
        if (token.getUsuarioId() == id) return true;
        else throw new PermissaoException();
    }
}
