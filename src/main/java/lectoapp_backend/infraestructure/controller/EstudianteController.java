package lectoapp_backend.infraestructure.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lectoapp_backend.application.dto.request.ActualizarEstudianteRequest;
import lectoapp_backend.application.dto.request.CrearEstudianteRequest;
import lectoapp_backend.application.dto.request.ValidarCodigoRequest;
import lectoapp_backend.application.dto.response.EstudianteResponse;
import lectoapp_backend.application.dto.response.ValidacionCodigoResponse;
import lectoapp_backend.application.mapper.EstudianteResponseMapper;
import lectoapp_backend.application.usecase.estudiantes.ActualizarEstudianteUseCase;
import lectoapp_backend.application.usecase.estudiantes.BuscarEstudianteUseCase;
import lectoapp_backend.application.usecase.estudiantes.CrearEstudianteUseCase;
import lectoapp_backend.application.usecase.estudiantes.DesactivarEstudianteUseCase;
import lectoapp_backend.application.usecase.estudiantes.ListarEstudiantesUseCase;
import lectoapp_backend.application.usecase.estudiantes.ValidarCodigoUseCase;
import lombok.RequiredArgsConstructor;

/**
 * Responsabilidad:
 * Expone los endpoints REST relacionados con los estudiantes.
 */

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@Validated
public class EstudianteController {

    private final CrearEstudianteUseCase crearUseCase;
    private final BuscarEstudianteUseCase buscarUseCase;
    private final ActualizarEstudianteUseCase actualizarUseCase;
    private final ListarEstudiantesUseCase listarUseCase;
    private final DesactivarEstudianteUseCase desactivarUseCase;
    private final ValidarCodigoUseCase validarCodigoUseCase;

    private final EstudianteResponseMapper responseMapper;

    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    @PostMapping
    public ResponseEntity<EstudianteResponse> crear(
            @Valid @RequestBody CrearEstudianteRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMapper.toResponse(
                        crearUseCase.ejecutar(request)));

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                responseMapper.toResponse(
                        buscarUseCase.ejecutar(id)));

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> listar() {

        return ResponseEntity.ok(

                listarUseCase.ejecutar()
                        .stream()
                        .map(responseMapper::toResponse)
                        .toList()

        );

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> actualizar(

            @PathVariable Long id,

            @Valid @RequestBody ActualizarEstudianteRequest request

    ) {

        return ResponseEntity.ok(

                responseMapper.toResponse(

                        actualizarUseCase.ejecutar(id, request)

                )

        );

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(

            @PathVariable Long id

    ) {

        desactivarUseCase.ejecutar(id);

        return ResponseEntity.noContent().build();

    }

    
    @PostMapping("/validar")
    public ResponseEntity<ValidacionCodigoResponse> validarCodigo(

            @Valid @RequestBody ValidarCodigoRequest request

    ) {

        return ResponseEntity.ok(

                responseMapper.toValidacionResponse(

                        validarCodigoUseCase.ejecutar(
                                request.getCodigoAcceso())

                )

        );

    }

}