package almoxarifadovirtual.servidor.servico;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ServicoLdap {

  private List<String[]> usuariosLdap;

  /**
   * Classe que faz um mock de um servidor Ldap.
   */
  public ServicoLdap() {
    this.usuariosLdap = new ArrayList<String[]>();
    this.usuariosLdap.add(new String[] {"Matteus", "passwd-admin"});
    this.usuariosLdap.add(new String[] {"Alessandro", "passwd-almoxarife"});
    this.usuariosLdap.add(new String[] {"Lucas", "passwd-almoxarife"});
    this.usuariosLdap.add(new String[] {"Bernard", "passwd-prestador"});
    this.usuariosLdap.add(new String[] {"Rafael", "passwd-prestador"});
  }

  /**
   * Método que verifica se usuário está cadastrado no servidor Ldap.
   * @param login - Login correspondente ao usuário que está sendo validado.
   * @param senha - Senha do usuário que está sendo validado.
   * @return True se existir um usuário com o mesmo login e senha.
   */
  public boolean validarUsuarioLdap(String login, String senha) {
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
