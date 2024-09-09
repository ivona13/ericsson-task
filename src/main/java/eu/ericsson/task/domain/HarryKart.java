package eu.ericsson.task.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "harryKart")
public class HarryKart {

    private int numberOfLoops;

    @JacksonXmlProperty(localName = "startList")
    private List<Participant> participants;

    private List<Loop> powerUps;
}
