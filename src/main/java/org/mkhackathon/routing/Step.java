package org.mkhackathon.routing;

public class Step {

    private final String instruction;
    private final Point point;
    private final int timeFromStart;
    private final double distanceFromStart;

    private Step(StepBuilder builder) {
        this.instruction = builder.instruction;
        this.point = builder.point;
        this.timeFromStart = builder.timeFromStart;
        this.distanceFromStart = builder.distanceFromStart;
    }

    public String getInstruction() {
        return instruction;
    }

    public Point getPoint() {
        return point;
    }

    public int getTimeFromStart() {
        return timeFromStart;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }


    public static StepBuilder builder() {
        return new StepBuilder();
    }

    public static final class StepBuilder {
        private String instruction;
        private Point point;
        private int timeFromStart;
        private double distanceFromStart;

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

        public StepBuilder withTimeFromStart(int timeFromStart) {
            this.timeFromStart = timeFromStart;
            return this;
        }

        public StepBuilder withDistanceFromStart(double distanceFromStart) {
            this.distanceFromStart = distanceFromStart;
            return this;
        }

        public Step build() {
            return new Step(this);
        }
    }
}
