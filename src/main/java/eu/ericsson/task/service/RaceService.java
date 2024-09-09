package eu.ericsson.task.service;

import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RaceService {
    public static final int TRACK_LENGTH = 1000;

    public double calculateTime(int speed) {
        return (double) TRACK_LENGTH / speed;
    }

    public List<Participant> calculateRank(HarryKart harryKart) {
        log.debug("Calculating the rank of the participants");
        List<Participant> winners = new ArrayList<>();
        harryKart.getParticipants().forEach(participant -> {
            log.debug("Processing participant: {}", participant.getName());
            int participantIndex = harryKart.getParticipants().indexOf(participant);
            for (int loop = 0; loop < harryKart.getNumberOfLoops(); loop++) {
                int speedToBeAdded = participant.getBaseSpeed();
                if (loop > 0) {
                    speedToBeAdded = harryKart.getPowerUps().get(loop - 1).getLanes().get(participantIndex).getPowerUp();
                }
                participant.addCurrentSpeed(speedToBeAdded);
                double currentTime = calculateTime(participant.getCurrentSpeed());
                participant.addToTotalTime(currentTime);
                log.debug("Total time for participant {}: {}", participant.getName(), participant.getTotalTime());
            }
            winners.add(participant);
        });

        winners.sort(Participant::compareTo);
        log.info("Finished calculating rank, winners: {}", winners);
        return winners;
    }
}
