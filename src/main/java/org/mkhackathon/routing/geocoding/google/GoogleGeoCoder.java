package org.mkhackathon.routing.geocoding.google;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Location;
import org.mkhackathon.routing.Point;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.geocoding.GeoCoder;
import org.mkhackathon.routing.geocoding.LocationNotFoundException;

public class GoogleGeoCoder implements GeoCoder {

    private final GeoApiContext geoApiContext;

    public GoogleGeoCoder(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    @Override
    public GeoCodedRoutingRequest geoCode(RoutingRequest routingRequest) {
        return GeoCodedRoutingRequest.builder(routingRequest)
                .withStart(geoCode(routingRequest.getStart()))
                .withEnd(geoCode(routingRequest.getEnd()))
                .build();
    }

    private Point geoCode(Location location) {
        try {
            return getPoint(location);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Point getPoint(Location location) throws Exception {
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, location.get()).await();
        if (results.length > 0) {
            return convertPoint(results[0].geometry.location);
        }
        throw new LocationNotFoundException(location);
    }

    private Point convertPoint(LatLng location) {
        return new Point(location.lat, location.lng);
    }

}
