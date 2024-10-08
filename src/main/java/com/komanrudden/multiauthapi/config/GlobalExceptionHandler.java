package com.komanrudden.multiauthapi.config;

import com.komanrudden.multiauthapi.model.error.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for handling exceptions across the whole application.
 * <p>
 * This class uses `@RestControllerAdvice` to provide centralized exception handling.
 * It extends `ResponseEntityExceptionHandler` to leverage Spring's built-in exception handling capabilities.
 * </p>
 *
 * <p>It handles the following exceptions:</p>
 * <ul>
 *   <li><b>AuthenticationException</b>: Returns a 401 Unauthorized status with an error message.</li>
 *   <li><b>BadRequestException</b>: Returns a 400 Bad Request status with an error message.</li>
 * </ul>
 *
 * <p>Each handler method logs the exception details using `@Slf4j` for logging.</p>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.UNAUTHORIZED
        );

        log.error("AuthenticationException: ", e);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(BadRequestException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        log.error("BadRequestException: ", e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }
}
