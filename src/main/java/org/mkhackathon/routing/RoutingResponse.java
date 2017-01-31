package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RoutingResponse {

    private final Route route;

    @JsonCreator
    public RoutingResponse(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
