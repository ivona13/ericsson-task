package eu.ericsson.task.controller.dto;

import lombok.Builder;

@Builder
public record ParticipantRankDTO(int rank, String name, double totalTime) {
}
