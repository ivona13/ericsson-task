package eu.ericsson.task.service;

import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import eu.ericsson.task.domain.RacingParticipant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RaceService {

    @Value("${application.race.track-length}")
    private int trackLength;

    public List<RacingParticipant> calculateRank(HarryKart harryKart) {
        log.debug("Calculating the rank of the participants");
        List<RacingParticipant> winners = new ArrayList<>();

        List<Participant> participants = harryKart.getParticipants();
        for (int participantIndex = 0; participantIndex < harryKart.getParticipants().size(); participantIndex++) {
            Participant participant = participants.get(participantIndex);
            RacingParticipant racingParticipant = new RacingParticipant(participant.getLane(), participant.getName(), participant.getBaseSpeed());
            log.debug("Processing participant: {}", racingParticipant.getName());
            for (int loop = 0; loop < harryKart.getNumberOfLoops(); loop++) {
                int speedToBeAdded = racingParticipant.getBaseSpeed();
                if (loop > 0) {
                    speedToBeAdded = harryKart.getPowerUps().get(loop - 1).getLane().get(participantIndex).getPowerUp();
                }
                racingParticipant.addCurrentSpeed(speedToBeAdded);
                double currentTime = calculateTime(racingParticipant.getCurrentSpeed());
                racingParticipant.addToTotalTime(currentTime);
                log.debug("Total time for participant {}: {}", racingParticipant.getName(), racingParticipant.getTotalTime());
            }
            winners.add(racingParticipant);
        }

        winners.sort(RacingParticipant::compareTo);
        log.info("Finished calculating rank, winners: {}", winners);
        return winners;
    }

    private double calculateTime(int speed) {
        return (double) trackLength / speed;
    }
}
