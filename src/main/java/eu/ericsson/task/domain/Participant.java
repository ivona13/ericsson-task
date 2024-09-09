package eu.ericsson.task.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement
public class Participant implements Comparable<Participant> {

    @JacksonXmlProperty
    private int lane;

    @JacksonXmlProperty
    private String name;

    @JacksonXmlProperty
    private int baseSpeed;

    private double totalTime;
    private int currentSpeed;

    public void addCurrentSpeed(int speed) {
        this.currentSpeed += speed;
    }

    public void addToTotalTime(double time) {
        this.totalTime += time;
    }

    @Override
    public int compareTo(Participant o) {
        return Double.compare(this.totalTime, o.totalTime);
    }
}