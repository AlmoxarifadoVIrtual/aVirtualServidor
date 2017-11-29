package almoxarifadovirtual.servidor.servico;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


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

  public boolean ehUsuarioLdap(String login, String senha) {
    for (String[] usuario : usuariosLdap) {
      if (usuario[0].equals(login) && usuario[1].equals(senha)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Método que verifica se o nome do usuário está cadastrado no LDAP.
   * @param nome - Nome a ser verificado.
   * @return True se o nome do usuário está cadastrado no sistema.
   */
  public boolean existeUsuario(String nome) {

    for (String[] usuario : usuariosLdap) {
      if (usuario[0].equals(nome)) {
        return true;
      }
    }

    return false;
  }
}
