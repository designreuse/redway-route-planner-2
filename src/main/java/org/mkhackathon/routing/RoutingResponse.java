package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoutingResponse {

    private final Route route;

    @JsonCreator
    public RoutingResponse(@JsonProperty("route") Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
