package eu.ericsson.task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantRankDTO {

    private int rank;
    private String name;
    private double totalTime;
}
