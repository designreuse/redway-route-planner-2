package org.mkhackathon.routing;

import java.util.List;

public class Route {

    List<Point> points;

    public Route(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }
}
