package org.mkhackathon.routing;

public interface Router {
    RoutingResponse getRoute(GeoCodedRoutingRequest request);
}
