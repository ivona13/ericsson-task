package eu.ericsson.task.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lane {

    @JacksonXmlProperty(isAttribute = true)
    private int number;

    @JacksonXmlText
    private int powerUp;
}
