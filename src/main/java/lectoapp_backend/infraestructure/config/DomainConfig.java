package lectoapp_backend.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lectoapp_backend.domain.repository.EstudianteRepository;
import lectoapp_backend.domain.service.GeneradorCodigoService;

/**
 * Responsabilidad:
 * Registra los servicios del dominio como Beans de Spring.
 * De esta forma el dominio permanece desacoplado del framework.
 */
@Configuration
public class DomainConfig {

    @Bean
    public GeneradorCodigoService generadorCodigoService(
            EstudianteRepository estudianteRepository) {

        return new GeneradorCodigoService(estudianteRepository);

    }

}