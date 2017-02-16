package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Step.StepBuilder.class)
public class Step {

    private final String instruction;
    private final Point point;
    private final int time;
    private final double distance;

    private Step(StepBuilder builder) {
        this.instruction = builder.instruction;
        this.point = builder.point;
        this.time = builder.time;
        this.distance = builder.distance;
    }

    public String getInstruction() {
        return instruction;
    }

    public Point getPoint() {
        return point;
    }

    public int getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }

    @JsonCreator
    public static StepBuilder builder() {
        return new StepBuilder();
    }

    public static final class StepBuilder {
        private String instruction;
        private Point point;
        private int time;
        private double distance;

        private StepBuilder() {
        }

        public StepBuilder withInstruction(String instruction) {
            this.instruction = instruction;
            return this;
        }

        public StepBuilder withPoint(Point point) {
            this.point = point;
            return this;
        }

        public StepBuilder withTime(int time) {
            this.time = time;
            return this;
        }

        public StepBuilder withDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public Step build() {
            return new Step(this);
        }
    }
}
