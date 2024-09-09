package eu.ericsson.task.configuration.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarryKartExceptionDTO {

    private int status;
    private String code;
    private String description;
}