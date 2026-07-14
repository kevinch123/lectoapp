package lectoapp_backend.infraestructure.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lectoapp_backend.application.dto.request.LoginRequest;
import lectoapp_backend.application.dto.response.LoginResponse;
import lectoapp_backend.application.usecase.login.LoginUseCase;
import lombok.RequiredArgsConstructor;

/**
 * Responsabilidad:
 * Exponer el endpoint de autenticación.
 *
 * Recibe las credenciales del usuario y delega el proceso
 * de autenticación al LoginUseCase.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    /**
     * Permite iniciar sesión utilizando correo y contraseña.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = loginUseCase.ejecutar(request);

        return ResponseEntity.ok(response);

    }

}
