package lectoapp_backend.application.service;


import lectoapp_backend.domain.model.Usuario;

public interface JwtService {

    /**
     * Genera un JWT para el usuario autenticado.
     */
    String generarToken(Usuario usuario);

    /**
     * Extrae el correo contenido dentro del JWT.
     */
    String extraerCorreo(String token);

    /**
     * Verifica si el token es válido.
     */
    boolean esTokenValido(String token, Usuario usuario);
}
