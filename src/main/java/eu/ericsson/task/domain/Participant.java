package eu.ericsson.task.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class Participant {

    @JacksonXmlProperty
    private int lane;

    @JacksonXmlProperty
    private String name;

    @JacksonXmlProperty
    private int baseSpeed;
}