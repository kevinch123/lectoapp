package lectoapp_backend.infraestructure.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lectoapp_backend.application.dto.request.LoginEstudianteRequest;
import lectoapp_backend.application.dto.request.LoginRequest;
import lectoapp_backend.application.dto.response.LoginResponse;
import lectoapp_backend.application.usecase.estudiantes.LoginEstudianteUseCase;
import lectoapp_backend.application.usecase.login.LoginUseCase;
import lombok.RequiredArgsConstructor;

/**
 * Responsabilidad:
 * Exponer los endpoints de autenticación.
 *
 * Recibe las credenciales del usuario o el código de estudiante
 * y delega el proceso al caso de uso correspondiente.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    private final LoginEstudianteUseCase loginEstudianteUseCase;

    /**
     * Permite iniciar sesión utilizando correo y contraseña.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = loginUseCase.ejecutar(request);

        return ResponseEntity.ok(response);

    }

    /**
     * Permite iniciar sesión como estudiante utilizando un código de acceso.
     */
    @PostMapping("/estudiante/login")
    public ResponseEntity<LoginResponse> loginEstudiante(
            @Valid @RequestBody LoginEstudianteRequest request) {

        LoginResponse response = loginEstudianteUseCase.ejecutar(request);

        return ResponseEntity.ok(response);

    }

}
