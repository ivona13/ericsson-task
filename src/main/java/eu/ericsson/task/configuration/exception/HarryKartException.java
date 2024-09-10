package eu.ericsson.task.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HarryKartException extends RuntimeException {

    String code;
    String message;

    public HarryKartException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
