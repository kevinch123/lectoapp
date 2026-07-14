package lectoapp_backend.infraestructure.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lectoapp_backend.application.service.JwtService;
import lectoapp_backend.domain.model.Usuario;

/**
 * Responsabilidad:
 * Implementar la generación y validación de tokens JWT.
 *
 * Esta clase encapsula toda la lógica relacionada con JWT,
 * evitando que el resto de la aplicación dependa directamente
 * de la librería JJWT.
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Genera un JWT para el usuario autenticado.
     */
    @Override
    public String generarToken(Usuario usuario) {

        Date fechaActual = new Date();

        Date fechaExpiracion = new Date(
                fechaActual.getTime() + expiration
        );

        return Jwts.builder()
                .subject(usuario.getCorreo())
                .issuedAt(fechaActual)
                .expiration(fechaExpiracion)
                .signWith(obtenerClave())
                .compact();
    }

    /**
     * Extrae el correo almacenado dentro del JWT.
     */
    @Override
    public String extraerCorreo(String token) {

        return obtenerClaims(token).getSubject();

    }

    /**
     * Valida que el token pertenezca al usuario y que no esté expirado.
     */
    @Override
    public boolean esTokenValido(String token, Usuario usuario) {

        String correo = extraerCorreo(token);

        return correo.equals(usuario.getCorreo())
                && !estaExpirado(token);

    }

    /**
     * Verifica si el token ya expiró.
     */
    private boolean estaExpirado(String token) {

        return obtenerClaims(token)
                .getExpiration()
                .before(new Date());

    }

    /**
     * Obtiene todos los Claims contenidos en el JWT.
     */
    private Claims obtenerClaims(String token) {

        return Jwts.parser()
                .verifyWith(obtenerClave())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    /**
     * Construye la clave utilizada para firmar y validar
     * los tokens JWT.
     */
    private SecretKey obtenerClave() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

    }

}