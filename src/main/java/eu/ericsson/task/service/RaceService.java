package eu.ericsson.task.service;

import eu.ericsson.task.configuration.exception.HarryKartException;
import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import eu.ericsson.task.domain.RacingParticipant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
public class RaceService {
    private static final String NUMBER_OF_LOOPS_CONSTRAINT = "Number of loops must be at least one";
    private static final String NUMBER_OF_PARTICIPANTS_CONSTRAINT = "There must be at least two participant";
    private static final String NUMBER_OF_POWER_UPS_CONSTRAINT = "Number of power ups must be equal to number of loops - 1";

    @Value("${application.race.track-length}")
    private int trackLength;

    public static void validateHarryKart(HarryKart harryKart) {
        if (harryKart.getNumberOfLoops() < 1) {
            throw new HarryKartException(INTERNAL_SERVER_ERROR.name(), NUMBER_OF_LOOPS_CONSTRAINT);
        }
        if (harryKart.getParticipants().size() < 2) {
            throw new HarryKartException(INTERNAL_SERVER_ERROR.name(), NUMBER_OF_PARTICIPANTS_CONSTRAINT);
        }
        if (harryKart.getPowerUps().size() != harryKart.getNumberOfLoops() - 1) {
            throw new HarryKartException(INTERNAL_SERVER_ERROR.name(), NUMBER_OF_POWER_UPS_CONSTRAINT);
        }
    }

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
