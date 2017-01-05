package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = RoutingRequest.RoutingRequestBuilder.class)
public class RoutingRequest {

    private final Location start;
    private final Location end;

    private RoutingRequest(RoutingRequestBuilder builder) {
        this.start = builder.start;
        this.end = builder.end;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    @JsonCreator
    public static RoutingRequestBuilder builder() {
        return new RoutingRequestBuilder();
    }

    public static final class RoutingRequestBuilder {
        private Location start;
        private Location end;

        private RoutingRequestBuilder() {
        }

        public RoutingRequestBuilder withStart(Location start) {
            this.start = start;
            return this;
        }

        public RoutingRequestBuilder withEnd(Location end) {
            this.end = end;
            return this;
        }

        public RoutingRequest build() {
            return new RoutingRequest(this);
        }
    }
}
