package eu.ericsson.task;

import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Lane;
import eu.ericsson.task.domain.Loop;
import eu.ericsson.task.domain.RacingParticipant;

import java.util.Arrays;
import java.util.List;

public class HarryKartSubObj {
    public static final String WAIKIKI_SILVIO = "WAIKIKI SILVIO";
    public static final String TIMETOBELUCKY = "TIMETOBELUCKY";
    public static final String HERCULES_BOKO = "HERCULES BOKO";
    public static final String CARGO_DOOR = "CARGO DOOR";

    public static HarryKart givenHarryKartStubObj() {
        return givenHarryKartBuilder().build();
    }

    public static HarryKart.HarryKartBuilder givenHarryKartBuilder() {
        return HarryKart.builder()
                .numberOfLoops(3)
                .powerUps(Arrays.asList(
                        createLoopStubObj(1, Arrays.asList(
                                createLaneStubObj(1, 0),
                                createLaneStubObj(2, 0),
                                createLaneStubObj(3, 1),
                                createLaneStubObj(4, 3)
                        )),
                        createLoopStubObj(2, Arrays.asList(
                                createLaneStubObj(1, 10),
                                createLaneStubObj(2, 0),
                                createLaneStubObj(3, 0),
                                createLaneStubObj(4, 1)
                        ))
                ))
                .participants(Arrays.asList(
                        createParticipantStubObj(1, TIMETOBELUCKY, 10),
                        createParticipantStubObj(2, CARGO_DOOR, 10),
                        createParticipantStubObj(3, HERCULES_BOKO, 10),
                        createParticipantStubObj(4, WAIKIKI_SILVIO, 10)
                ));
    }

    public static RacingParticipant createParticipantStubObj(int lane, String name, int baseSpeed) {
        return RacingParticipant.builder()
                .lane(lane)
                .name(name)
                .baseSpeed(baseSpeed)
                .build();
    }

    public static RacingParticipant createParticipantStubObj(int lane, String name, double totalTime) {
        return RacingParticipant.builder()
                .lane(lane)
                .name(name)
                .totalTime(totalTime)
                .build();
    }

    private static Lane createLaneStubObj(int number, int powerUp) {
        return Lane.builder()
                .number(number)
                .powerUp(powerUp)
                .build();
    }

    private static Loop createLoopStubObj(int number, List<Lane> lanes) {
        return Loop.builder()
                .number(number)
                .lane(lanes)
                .build();
    }
}
