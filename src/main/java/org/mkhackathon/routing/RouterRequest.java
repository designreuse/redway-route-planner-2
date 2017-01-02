package org.mkhackathon.routing;

/**
 * Created by louis on 30/12/2016.
 */
public class RouterRequest {

    private final Point start;
    private final Point end;

    public RouterRequest(RouterRequestBuilder builder) {
        this.start = builder.start;
        this.end = builder.end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public static RouterRequestBuilder builder() {
        return new RouterRequestBuilder();
    }

    public static final class RouterRequestBuilder {
        private Point start;
        private Point end;

        private RouterRequestBuilder() {
        }

        public RouterRequestBuilder withStart(Point start) {
            this.start = start;
            return this;
        }

        public RouterRequestBuilder withEnd(Point end) {
            this.end = end;
            return this;
        }

        public RouterRequest build() {
            return new RouterRequest(this);
        }
    }
}
