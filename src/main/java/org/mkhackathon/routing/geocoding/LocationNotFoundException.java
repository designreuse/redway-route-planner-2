package org.mkhackathon.routing.geocoding;

import org.mkhackathon.routing.Location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Location location) {
        this(location + " could not be found");
    }
}
