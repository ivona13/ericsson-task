package eu.ericsson.task.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class RacingParticipant extends Participant {

    private double totalTime;
    private int currentSpeed;

    public RacingParticipant(int lane, String name, int baseSpeed) {
        this(lane, name, baseSpeed, 0, 0);
    }

    @Builder
    public RacingParticipant(int lane, String name, int baseSpeed, double totalTime, int currentSpeed) {
        super(lane, name, baseSpeed);
        this.totalTime = totalTime;
        this.currentSpeed = currentSpeed;
    }

    public void addCurrentSpeed(int speed) {
        this.currentSpeed += speed;
    }

    public void addToTotalTime(double time) {
        this.totalTime += time;
    }
}
