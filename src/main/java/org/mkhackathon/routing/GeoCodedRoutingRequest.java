package org.mkhackathon.routing;

public class GeoCodedRoutingRequest {

    private final Point start;
    private final Point end;
    private final RoutingRequest routingRequest;

    GeoCodedRoutingRequest(RouterRequestBuilder builder) {
        this.start = builder.start;
        this.end = builder.end;
        this.routingRequest = builder.routingRequest;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public static RouterRequestBuilder builder(RoutingRequest routingRequest) {
        return new RouterRequestBuilder(routingRequest);
    }

    public static final class RouterRequestBuilder {
        private Point start;
        private Point end;
        private RoutingRequest routingRequest;

        private RouterRequestBuilder(RoutingRequest routingRequest) {
            this.routingRequest = routingRequest;
        }

        public RouterRequestBuilder withStart(Point start) {
            this.start = start;
            return this;
        }

        public RouterRequestBuilder withEnd(Point end) {
            this.end = end;
            return this;
        }

        public GeoCodedRoutingRequest build() {
            return new GeoCodedRoutingRequest(this);
        }
    }
}
