package org.mkhackathon.routing.graphhopper;

import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.util.shapes.BBox;
import com.graphhopper.util.shapes.GHPoint3D;
import org.apache.tomcat.jni.Local;
import org.mkhackathon.routing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GraphhopperRouter implements Router {

    private final GraphHopperWeb graphHopperWeb;

    @Autowired
    public GraphhopperRouter(GraphHopperWeb graphHopperWeb) {
        this.graphHopperWeb = graphHopperWeb;
    }

    @Override
    public RoutingResponse getRoute(GeoCodedRoutingRequest request) {
        GHResponse ghResponse = graphHopperWeb.route(GraphhopperRequestFactory.build(request));
        if (!ghResponse.hasErrors()) {
            return buildResponse(ghResponse);
        }
        throw new RoutingException(ghResponse.getErrors().get(0));
    }

    private RoutingResponse buildResponse(GHResponse ghResponse) {
        PathWrapper pathWrapper = ghResponse.getBest();

        return new RoutingResponse(Route.builder()
                .withEta(getEta(pathWrapper))
                .withPoints(convertPoints(pathWrapper))
                .withSteps(StepConverter.convert(pathWrapper.getInstructions()))
                .withBoundingBox(getBoundingBox(pathWrapper))
                .build());
    }

    private LocalDateTime getEta(PathWrapper pathWrapper) {
        LocalDateTime now = LocalDateTime.now();
        return now.plus(pathWrapper.getTime(), ChronoField.MILLI_OF_DAY.getBaseUnit());
    }



    private List<Point> convertPoints(PathWrapper pathWrapper) {
        return StreamSupport.stream(pathWrapper.getPoints().spliterator(), false)
        .map(this::convertPoint)
        .collect(Collectors.toList());
    }

    private BoundingBox getBoundingBox(PathWrapper pathWrapper) {
        BBox bBox = pathWrapper.calcRouteBBox(BBox.createInverse(false));
        return new BoundingBox(bBox.minLat, bBox.maxLat, bBox.minLon, bBox.maxLon);
    }

    private Point convertPoint(GHPoint3D ghPoint3D) {
        return new Point(ghPoint3D.getLat(), ghPoint3D.getLon());
    }


}
