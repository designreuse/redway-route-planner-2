package org.mkhackathon.routing.geocoding;

import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.RoutingRequest;

public interface GeoCoder {

    GeoCodedRoutingRequest geoCode(RoutingRequest routingRequest);
}
