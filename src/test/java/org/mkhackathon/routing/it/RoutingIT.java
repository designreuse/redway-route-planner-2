package org.mkhackathon.routing.it;

import org.junit.Before;
import org.junit.Test;
import org.mkhackathon.routing.Location;
import org.mkhackathon.routing.Route;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.RoutingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mkhackathon.routing.SslUtil.turnOffSslChecking;

public class RoutingIT {

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        turnOffSslChecking();
    }

    @Test
    public void should_return_route() throws Exception {
        ResponseEntity<RoutingResponse> responseEntity =
                restTemplate.postForEntity("http://localhost:8443/routing", RoutingRequest.builder()
                        .withStart(new Location("Wolverton"))
                        .withEnd(new Location("Walnut Tree"))
                        .build(), RoutingResponse.class);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        RoutingResponse routingResponse = responseEntity.getBody();
        Route route = routingResponse.getRoute();

        assertThat(route.getEta().isAfter(LocalDateTime.now()), is(true));
        assertFalse(route.getPoints().isEmpty());
        assertFalse(route.getSteps().isEmpty());
        assertNotNull(route.getBoundingBox());

    }

}
