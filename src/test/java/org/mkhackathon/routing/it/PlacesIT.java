package org.mkhackathon.routing.it;

import org.junit.Before;
import org.junit.Test;
import org.mkhackathon.places.PlaceOfInterest;
import org.mkhackathon.routing.Location;
import org.mkhackathon.routing.Route;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.RoutingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mkhackathon.routing.SslUtil.turnOffSslChecking;

/**
 * Created by louis on 20/02/2017.
 */
public class PlacesIT {

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        turnOffSslChecking();
    }

    @Test
    public void should_return_route() throws Exception {
        ResponseEntity<PlaceOfInterest[]> responseEntity =
                restTemplate.getForEntity("https://localhost:8443/places", PlaceOfInterest[].class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        PlaceOfInterest[] places = responseEntity.getBody();

        assertThat(places.length, is(144));
        assertThat(places[24].getName(), equalTo("Xscape: Diane Maclean, 2000"));
    }
}
