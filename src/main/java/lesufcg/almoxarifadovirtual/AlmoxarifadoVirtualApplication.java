package lesufcg.almoxarifadovirtual;

import lesufcg.almoxarifadovirtual.controle.ControleUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AlmoxarifadoVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmoxarifadoVirtualApplication.class, args);
	}
}
