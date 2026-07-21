package lectoapp_backend.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiError> manejarValidaciones(
                MethodArgumentNotValidException ex,
                HttpServletRequest request
        ) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Error de validación");

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(mensaje)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);

        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> manejarGeneral(
                Exception ex,
                HttpServletRequest request
        ) {

        ex.printStackTrace();

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);

        }
        /**
         * Maneja las excepciones cuando un recurso solicitado
         * no existe en el sistema.
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiError> manejarRecursoNoEncontrado(
                ResourceNotFoundException ex,
                HttpServletRequest request
        ) {

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);

        }
        // @ExceptionHandler(Exception.class)
        // public ResponseEntity<ApiError> manejarGeneral(
        //         Exception ex,
        //         HttpServletRequest request
        // ) {

        // ApiError error = ApiError.builder()
        //         .timestamp(LocalDateTime.now())
        //         .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        //         .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        //         .message("Ha ocurrido un error interno.")
        //         .path(request.getRequestURI())
        //         .build();

        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        //         .body(error);

        // }

}
