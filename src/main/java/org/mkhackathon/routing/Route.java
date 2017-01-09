package org.mkhackathon.routing;

import java.util.List;

public class Route {

    private List<Point> points;
    private BoundingBox boundingBox;

    public Route(List<Point> points, BoundingBox boundingBox) {
        this.points = points;
        this.boundingBox = boundingBox;
    }

    public List<Point> getPoints() {
        return points;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
