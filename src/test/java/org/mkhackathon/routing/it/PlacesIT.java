package org.mkhackathon.routing.it;

import org.junit.Before;
import org.junit.Test;
import org.mkhackathon.places.PlaceOfInterest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mkhackathon.routing.SslUtil.turnOffSslChecking;

public class PlacesIT {

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        turnOffSslChecking();
    }

    @Test
    public void should_return_route() throws Exception {
        ResponseEntity<PlaceOfInterest[]> responseEntity =
                restTemplate.getForEntity("http://localhost:8443/places", PlaceOfInterest[].class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        PlaceOfInterest[] places = responseEntity.getBody();

        assertThat(places.length, is(144));
        assertThat(places[24].getName(), equalTo("Xscape: Diane Maclean, 2000"));
    }
}
