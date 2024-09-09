package eu.ericsson.task.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantRankDTO {

    private int rank;
    private String name;
    private double totalTime;
}
