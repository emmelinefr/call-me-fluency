package dev.alexandraemmeline.call_me_fluency.Infrastructure.Handler;

import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.DomainException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.EmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException (DomainException ex) {

        ErrorResponse response = new ErrorResponse(
                false,
                ex.getMessage(),
                List.of(ex.getMessage()),
                LocalDateTime.now()
        );


        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyExistsException(EmailAlreadyExistsException ex) {

        ErrorResponse response = new ErrorResponse(
                false,
                ex.getMessage(),
                List.of(ex.getMessage()),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest()
                .body(response);
    }

}
