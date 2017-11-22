package almoxarifadovirtual.servidor.util;

public class LoginException extends RuntimeException{

    public LoginException(){
        super("Usuário não cadastrado no sistema!");
    }
}
