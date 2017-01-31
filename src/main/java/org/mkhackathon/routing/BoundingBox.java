package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BoundingBox {
    private final double minLat;
    private final double maxLat;
    private final double minLng;
    private final double maxLng;

    @JsonCreator
    public BoundingBox(@JsonProperty("minLat") double minLat,
                       @JsonProperty("maxLat") double maxLat,
                       @JsonProperty("minLng") double minLng,
                       @JsonProperty("maxLng") double maxLng) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLng = minLng;
        this.maxLng = maxLng;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMinLng() {
        return minLng;
    }

    public double getMaxLng() {
        return maxLng;
    }
}
