package org.mkhackathon.places;

import org.mkhackathon.routing.Point;

/**
 * A class to model a place of interest of the map. This could be a piece of
 * public art or it could be a public toilet.
 */
public class PlaceOfInterest {

    private final String name;
    private final Point point;

    public PlaceOfInterest(String name, Point point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }
}
