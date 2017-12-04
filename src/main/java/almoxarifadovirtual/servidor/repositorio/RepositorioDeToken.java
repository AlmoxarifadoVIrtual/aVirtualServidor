package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.autenticacao.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioDeToken extends JpaRepository<Token, Long> {

  Token findByChave(String chave);

  Token findByUsuarioId(Long usuarioId);
}
