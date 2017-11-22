package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import almoxarifadovirtual.servidor.util.LoginException;
import almoxarifadovirtual.servidor.util.PermissaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoControle {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @Autowired
    private ServicoAutenticacao servicoAutenticacao;

    public String logIn(Credenciais credenciais){
        if(!ehUsuarioLdap(credenciais))
            throw new LoginException();
        
        Usuario usuario = servicoUsuario.get(credenciais.getLogin());

        if(usuario != null) {
            Token token = servicoAutenticacao.gerarToken(usuario.getId());
            return token.getChave();
        }

        else throw new LoginException();

    }

    //Métodos do Usuário

    public Usuario criarUsuario(Usuario usuario, String chave) {
        if (validarToken(chave) && validarAdmin(chave)) {
            return servicoUsuario.create(usuario);
        }
        return null;
    }

    public Usuario getUsuario(Long id, String chave) {

        if (validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
            return servicoUsuario.get(id);
        }

        return null;
    }

    public List<Usuario> getAllUsuarios(String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return servicoUsuario.getAll();
        }

        return null;
    }

    public boolean atualizarUsuario(Usuario usuario, String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return servicoUsuario.update(usuario);
        }

        return false;
    }

    public boolean deletarUsuario(Long id, String chave) {

        if (validarToken(chave) && (validarAdmin(chave) || validarId(chave, id))) {
            return servicoUsuario.delete(id);
        }

        return false;
    }

    public List<Usuario> getUsuarioByFuncao(FuncaoUsuario tipo, String chave) {

        if (validarToken(chave) && validarAdmin(chave)) {
            return servicoUsuario.get(tipo);
        }

        return null;
    }

    //Métodos de autenticação

    public void deletarToken(Token token) {
        servicoAutenticacao.deletarToken(token);
    }

    public Token getTokenByChave(String chave) {
        return servicoAutenticacao.getTokenByChave(chave);
    }

    public Token getTokenByUsuarioId(Long usuarioId) {
        return servicoAutenticacao.getTokenByUsuarioId(usuarioId);
    }

    public Token gerarToken(Long usuarioId) {
        return servicoAutenticacao.gerarToken(usuarioId);
    }

    private boolean validarToken(String chave) {
        return this.servicoAutenticacao.validarToken(chave);
    }

    // Métodos auxiliares de validação

    private boolean validarAdmin(String chave) {

        Token token = servicoAutenticacao.getTokenByChave(chave);
        Usuario usuario = servicoUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.ADMINISTRADOR) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ADMINISTRADOR);
    }

    private boolean validarAlmoxarife(String chave) {

        Token token = servicoAutenticacao.getTokenByChave(chave);
        Usuario usuario = servicoUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.ALMOXARIFE) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.ALMOXARIFE);
    }

    private boolean validarPrestador(String chave) {

        Token token = servicoAutenticacao.getTokenByChave(chave);
        Usuario usuario = servicoUsuario.get(token.getUsuarioId());

        if (usuario.getFuncao() == FuncaoUsuario.PRESTADOR) return true;
        else throw new PermissaoException(usuario.getFuncao(), FuncaoUsuario.PRESTADOR);
    }

    private boolean validarId(String chave, Long id) {

        Token token = servicoAutenticacao.getTokenByChave(chave);
        if (token.getUsuarioId() == id) return true;
        else throw new PermissaoException();
    }


    private boolean ehUsuarioLdap(Credenciais credenciais) {
        // Mock da parte de login com uma lista
        List<String[]> usuariosLdap = new ArrayList<String[]>();
        usuariosLdap.add(new String[]{"Matteus", "passwd-admin"});
        usuariosLdap.add(new String[]{"Alessandro", "passwd-almoxarife"});
        usuariosLdap.add(new String[]{"Lucas", "passwd-almoxarife"});
        usuariosLdap.add(new String[]{"Bernardi", "passwd-prestador"});
        usuariosLdap.add(new String[]{"Rafael", "passwd-prestador"});

        boolean ehUsuarioValido = false;
        for (String[] item : usuariosLdap) {
            if(item[0].equals(credenciais.getLogin()) && item[1].equals(credenciais.getSenha())) {
                ehUsuarioValido = true;
                break;
            }
        }
        return ehUsuarioValido;
    }
}
