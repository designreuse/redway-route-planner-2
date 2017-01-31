package org.mkhackathon.routing.geocoding.osm;

import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.model.Address;
import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Location;
import org.mkhackathon.routing.Point;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.geocoding.GeoCoder;
import org.mkhackathon.routing.geocoding.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class OsmGeoCoder implements GeoCoder {

    private final NominatimClient nominatimClient;

    @Autowired
    public OsmGeoCoder(NominatimClient nominatimClient) {
        this.nominatimClient = nominatimClient;
    }


    @Override
    public GeoCodedRoutingRequest geoCode(RoutingRequest routingRequest) {
        return GeoCodedRoutingRequest.builder(routingRequest)
                .withStart(geoCode(routingRequest.getStart()))
                .withEnd(geoCode(routingRequest.getEnd()))
                .build();
    }

    private Point geoCode(Location location) {
        Address address = getAddressFromLocation(location);
        return new Point(address.getLatitude(), address.getLongitude());
    }

    private Address getAddressFromLocation(Location location) {
        List<Address> addresses = getAddresses(location);
        if (!addresses.isEmpty()) {
            return addresses.get(0);
        }
        throw new LocationNotFoundException(location);
    }

    private List<Address> getAddresses(Location location) {
        try {
            return nominatimClient.search(location.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
