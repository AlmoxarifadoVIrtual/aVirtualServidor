package almoxarifadovirtual.servidor.repositorio;

import almoxarifadovirtual.servidor.modelo.operacao.Operacao;
import almoxarifadovirtual.servidor.modelo.operacao.TipoDeOperacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeOperacao extends JpaRepository<Operacao, Long> {

  List<Operacao> findByUsuarioId(Long usuarioId);

  List<Operacao> findByTipoDeOperacao(TipoDeOperacao tipoDeOperacao);

}
