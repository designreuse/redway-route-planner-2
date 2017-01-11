package org.mkhackathon.routing;

import java.util.List;

public class Route {

    private final List<Point> points;
    private final BoundingBox boundingBox;
    private final List<Step> steps;

    public Route(List<Point> points, BoundingBox boundingBox, List<Step> steps) {
        this.points = points;
        this.boundingBox = boundingBox;
        this.steps = steps;
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
}
