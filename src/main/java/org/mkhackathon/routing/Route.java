package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

@JsonDeserialize(builder = Route.RouteBuilder.class)
public class Route {

    private final LocalDateTime eta;
    private final int minutes;
    private final List<Point> points;
    private final BoundingBox boundingBox;
    private final List<Step> steps;

    private Route(RouteBuilder builder) {
        this.eta = builder.eta;
        this.minutes = builder.minutes;
        this.points = builder.points;
        this.boundingBox = builder.boundingBox;
        this.steps = builder.steps;
    }

    public LocalDateTime getEta() {
        return eta;
    }

    public int getMinutes() {
        return minutes;
    }

    public List<Point> getPoints() {
        return points;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @JsonCreator
    public static RouteBuilder builder() {
        return new RouteBuilder();
    }

    public static final class RouteBuilder {
        private LocalDateTime eta;
        private int minutes;
        private List<Point> points;
        private BoundingBox boundingBox;
        private List<Step> steps;

        private RouteBuilder() {
        }

        public RouteBuilder withEta(LocalDateTime eta) {
            this.eta = eta;
            return this;
        }

        public RouteBuilder withMinutes(int minutes) {
            this.minutes = minutes;
            return this;
        }

        public RouteBuilder withPoints(List<Point> points) {
            this.points = points;
            return this;
        }

        public RouteBuilder withBoundingBox(BoundingBox boundingBox) {
            this.boundingBox = boundingBox;
            return this;
        }

        public RouteBuilder withSteps(List<Step> steps) {
            this.steps = steps;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
