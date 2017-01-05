package org.mkhackathon.routing;

public class RoutingResponse {

    private final Route route;

    public RoutingResponse(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
