package lectoapp_backend.infraestructure.controller;


import jakarta.validation.Valid;
import lectoapp_backend.application.dto.request.UsuarioRequest;
import lectoapp_backend.application.dto.response.UsuarioResponse;
import lectoapp_backend.application.usecase.ActualizarUsuarioUseCase;
import lectoapp_backend.application.usecase.BuscarUsuarioPorIdUseCase;
import lectoapp_backend.application.usecase.CrearUsuarioUseCase;
import lectoapp_backend.application.usecase.EliminarUsuarioUseCase;
import lectoapp_backend.application.usecase.ListarUsuariosUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    private final EliminarUsuarioUseCase eliminarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @Valid @RequestBody UsuarioRequest request) {

        UsuarioResponse response = crearUsuarioUseCase.ejecutar(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(){

        return ResponseEntity.ok(
                listarUsuariosUseCase.ejecutar()
        );

    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(
            @PathVariable Long id){

        return ResponseEntity.ok(
                buscarUsuarioPorIdUseCase.ejecutar(id)
        );

    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request){

        UsuarioResponse response = actualizarUsuarioUseCase.ejecutar(id, request);

        return ResponseEntity.ok(response);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
            @PathVariable Long id){

        eliminarUsuarioUseCase.ejecutar(id);

        return ResponseEntity.noContent().build();

    }

}
