package eu.ericsson.task;

import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.RacingParticipant;
import eu.ericsson.task.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static eu.ericsson.task.HarryKartSubObj.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RaceServiceTest {

    @InjectMocks
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(raceService, "trackLength", 1000);
    }

    @Test
    void calculateWinner_ShouldReturnSortedParticipants() {
        HarryKart harryKart = givenHarryKartStubObj();

        List<RacingParticipant> result = raceService.calculateRank(harryKart);

        assertEquals(4, result.size());
        assertEquals(WAIKIKI_SILVIO, result.getFirst().getName());
        assertEquals(TIMETOBELUCKY, result.get(1).getName());
        assertEquals(HERCULES_BOKO, result.get(2).getName());

        assertEquals(248.35, result.get(0).getTotalTime(), 0.01);
        assertEquals(250, result.get(1).getTotalTime());
        assertEquals(281.81, result.get(2).getTotalTime(), 0.01);
    }
}
