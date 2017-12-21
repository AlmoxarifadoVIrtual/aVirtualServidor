package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.usuario.FuncaoUsuario;
import almoxarifadovirtual.servidor.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CarregadorDeUsuario implements ApplicationRunner {


  private final RepositorioDeUsuario repositorioDeUsuario;

  @Autowired
  public CarregadorDeUsuario(RepositorioDeUsuario repositorioDeUsuario) {
    this.repositorioDeUsuario = repositorioDeUsuario;
  }

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    Usuario admin = new Usuario();
    admin.setFuncao(FuncaoUsuario.ADMINISTRADOR);
    admin.setNome("Alessandro");
    repositorioDeUsuario.save(admin);
  }
}