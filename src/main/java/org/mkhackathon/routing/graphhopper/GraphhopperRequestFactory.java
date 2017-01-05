package org.mkhackathon.routing.graphhopper;

import com.graphhopper.GHRequest;
import com.graphhopper.util.shapes.GHPoint;
import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Point;
import org.mkhackathon.routing.RoutingRequest;

public class GraphhopperRequestFactory {

    public static final String VEHICLE = "bike";

    static GHRequest build(GeoCodedRoutingRequest request) {
        GHRequest ghRequest = new GHRequest();
        ghRequest.setVehicle(VEHICLE);

        Point start = request.getStart();
        ghRequest.addPoint(new GHPoint(start.getLat(), start.getLng()));
        Point end = request.getEnd();
        ghRequest.addPoint(new GHPoint(end.getLat(), end.getLng()));

        return ghRequest;
    }
}
