package eu.ericsson.task.configuration;

import eu.ericsson.task.configuration.exception.HarryKartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleInvalidInput(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex),
                BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleAuthenticationErrorResponseException(AuthenticationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                ProblemDetail.forStatusAndDetail(
                        UNAUTHORIZED,
                        "Could not authenticate user"
                ), UNAUTHORIZED
        );
    }

    @ExceptionHandler(HarryKartException.class)
    public ResponseEntity<ProblemDetail> handleApplicationError(HarryKartException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                handleHarryKartException(ex),
                INTERNAL_SERVER_ERROR
        );
    }

    private ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problemDetail.setTitle("Bad request");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    private ProblemDetail handleHarryKartException(HarryKartException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(ex.getCode()), ex.getMessage());
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problemDetail.setTitle("Internal server error");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
