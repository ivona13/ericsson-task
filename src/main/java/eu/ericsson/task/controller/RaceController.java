package eu.ericsson.task.controller;

import eu.ericsson.task.controller.dto.ParticipantRankDTO;
import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import eu.ericsson.task.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RaceController {
    private static final int NUMBER_OF_WINNERS = 3;

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping(path = "/calculate-winner", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<ParticipantRankDTO> calculateWinner(@RequestBody HarryKart xml) {
        log.debug("Calculating winners for HarryKart");
        List<Participant> sortedParticipants = raceService.calculateRank(xml);

        return IntStream.range(0, Math.min(NUMBER_OF_WINNERS, sortedParticipants.size()))
                .mapToObj(i -> ParticipantRankDTO.builder()
                        .rank(i + 1)
                        .name(sortedParticipants.get(i).getName())
                        .totalTime(sortedParticipants.get(i).getTotalTime())
                        .build())
                .toList();
    }
}
