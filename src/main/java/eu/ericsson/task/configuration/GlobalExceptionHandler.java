package eu.ericsson.task.configuration;

import eu.ericsson.task.configuration.exception.HarryKartExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HarryKartExceptionDTO handleInvalidInput(Exception ex) {
        log.error(ex.getMessage(), ex);
        return HarryKartExceptionDTO.builder()
                .code(BAD_REQUEST.name())
                .status(BAD_REQUEST.value())
                .description("Invalid input: " + ex.getMessage())
                .build();
    }
}
