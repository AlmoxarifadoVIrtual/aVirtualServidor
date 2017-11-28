package almoxarifadovirtual.servidor.servico;

import almoxarifadovirtual.servidor.modelo.autenticacao.Credenciais;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoLdap {

  private List<String[]> usuariosLdap;

  public ServicoLdap() {
    this.usuariosLdap = new ArrayList<String[]>();
    this.usuariosLdap.add(new String[] {"Matteus", "passwd-admin"});
    this.usuariosLdap.add(new String[] {"Alessandro", "passwd-almoxarife"});
    this.usuariosLdap.add(new String[] {"Lucas", "passwd-almoxarife"});
    this.usuariosLdap.add(new String[] {"Bernard", "passwd-prestador"});
    this.usuariosLdap.add(new String[] {"Rafael", "passwd-prestador"});
  }

  public boolean ehUsuarioLdap(Credenciais credenciais) {

    boolean ehUsuarioValido = false;
    for (String[] usuario : usuariosLdap) {
      if (usuario[0].equals(credenciais.getLogin()) && usuario[1].equals(credenciais.getSenha())) {
        ehUsuarioValido = true;
        break;
      }
    }
    return ehUsuarioValido;
  }
}
