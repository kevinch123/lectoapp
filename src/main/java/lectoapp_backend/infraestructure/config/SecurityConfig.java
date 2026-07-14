package lectoapp_backend.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lectoapp_backend.infraestructure.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Responsabilidad:
 * Configurar la seguridad de toda la aplicación.
 *
 * La API utiliza autenticación JWT, por lo tanto:
 * - No existen sesiones HTTP.
 * - Cada petición protegida debe enviar un JWT válido.
 * - El filtro JwtAuthenticationFilter valida el token antes de que Spring procese la solicitud.
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // API REST + JWT
            .csrf(csrf -> csrf.disable())

            // No utilizar sesiones
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Configuración de permisos
            .authorizeHttpRequests(auth -> auth

                    // Login
                    .requestMatchers("/auth/login").permitAll()

                    // Acceso de estudiantes mediante código
                    .requestMatchers("/api/estudiantes/validar").permitAll()

                    // Todo lo demás necesita JWT
                    .anyRequest().authenticated()

            )

            // Registrar el filtro JWT
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();

    }

    /**
     * BCrypt utilizado para almacenar contraseñas.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    /**
     * AuthenticationManager utilizado durante el login.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

}