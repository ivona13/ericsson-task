package eu.ericsson.task.controller;

import eu.ericsson.task.controller.dto.ParticipantRankDTO;
import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import eu.ericsson.task.service.RaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
@RequestMapping("/api/v1")
@Tag(name = "race", description = "Operations related to race")
public class RaceController {

    @Value("${application.race.number-of-winners}")
    private int numberOfWinners;

    final RaceService raceService;

    @Operation(summary = "Calculates winners for HarryKart",
            description = "Calculates first three places for HarryKart based on the input XML file")
    @PostMapping(path = "/calculate-winner", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<ParticipantRankDTO> calculateWinner(@RequestBody HarryKart xml) {
        log.debug("Calculating winners for HarryKart");
        List<Participant> sortedParticipants = raceService.calculateRank(xml);

        return IntStream.range(0, Math.min(numberOfWinners, sortedParticipants.size()))
                .mapToObj(i -> ParticipantRankDTO.builder()
                        .rank(i + 1)
                        .name(sortedParticipants.get(i).getName())
                        .totalTime(sortedParticipants.get(i).getTotalTime())
                        .build())
                .toList();
    }
}
