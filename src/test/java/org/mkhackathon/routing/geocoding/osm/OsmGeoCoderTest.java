package org.mkhackathon.routing.geocoding.osm;


import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Location;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.geocoding.LocationNotFoundException;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OsmGeoCoderTest {

    @Mock
    private NominatimClient nominatimClient;

    private OsmGeoCoder geoCoder;

    @Before
    public void setUp() throws Exception {
        geoCoder = new OsmGeoCoder(nominatimClient);
    }

    @Test
    public void should_throw_exception_if_no_addresses_found() throws Exception {
        when(nominatimClient.search("Walton Park")).thenReturn(Collections.emptyList());

        try {
            GeoCodedRoutingRequest geoCodedRoutingRequest = geoCoder.geoCode(RoutingRequest.builder()
                    .withStart(new Location("Walton Park"))
                    .withEnd(new Location("Wolverton"))
                    .build());
        } catch (LocationNotFoundException exception) {
            assertThat(exception.getMessage(), equalTo("Walton Park, Milton Keynes could not be found"));
            return;
        }
        fail("Exception not thrown");
    }

    @Test
    public void should_create_start_point_from_first_address() throws Exception {
        Location start = new Location("Walton Park");
        Location end = new Location("Wolverton");
        setupLocationSearch(0.3334, 0.65656, start.get());
        setupLocationSearch(0.1111, 0.87776, end.get());

        GeoCodedRoutingRequest geoCodedRoutingRequest = geoCoder.geoCode(RoutingRequest.builder()
                .withStart(start)
                .withEnd(end)
                .build());

        assertThat(geoCodedRoutingRequest.getStart().getLat(), equalTo(0.3334));
        assertThat(geoCodedRoutingRequest.getStart().getLng(), equalTo(0.65656));
    }

    private void setupLocationSearch(double lat, double lng, String location) throws IOException {
        Address address = new Address();
        address.setLatitude(lat);
        address.setLongitude(lng);
        when(nominatimClient.search(location)).thenReturn(Collections.singletonList(address));
    }

}