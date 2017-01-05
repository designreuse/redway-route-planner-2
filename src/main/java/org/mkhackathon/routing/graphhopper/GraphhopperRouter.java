package org.mkhackathon.routing.graphhopper;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.util.shapes.GHPoint;
import com.graphhopper.util.shapes.GHPoint3D;
import okhttp3.OkHttpClient;
import org.mkhackathon.routing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
        throw new RuntimeException(ghResponse.getErrors().get(0));
    }

    private RoutingResponse buildResponse(GHResponse ghResponse) {
        PathWrapper pathWrapper = ghResponse.getBest();
        List<Point> points =
                StreamSupport.stream(pathWrapper.getPoints().spliterator(), false)
                .map(this::convertPoint)
                .collect(Collectors.toList());
        return new RoutingResponse(new Route(points));
    }

    private Point convertPoint(GHPoint3D ghPoint3D) {
        return new Point(ghPoint3D.getLat(), ghPoint3D.getLon());
    }


}
