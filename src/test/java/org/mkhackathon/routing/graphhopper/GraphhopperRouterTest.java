package org.mkhackathon.routing.graphhopper;

import com.graphhopper.GHResponse;
import com.graphhopper.api.GHMRequest;
import com.graphhopper.api.GHMResponse;
import com.graphhopper.api.GraphHopperWeb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Point;
import org.mkhackathon.routing.RoutingException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GraphhopperRouterTest {

    @Mock
    private GraphHopperWeb graphHopperWeb;

    private GraphhopperRouter router;

    @Before
    public void setUp() throws Exception {
        router = new GraphhopperRouter(graphHopperWeb);
    }

    @Test
    public void should_throw_exception_if_errors_from_graphhopper() throws Exception {
        GHResponse ghResponse = new GHResponse();
        RuntimeException exception = new RuntimeException();
        ghResponse.addError(exception);
        when(graphHopperWeb.route(Mockito.any(GHMRequest.class))).thenReturn(ghResponse);
        try {
            router.getRoute(GeoCodedRoutingRequest.builder(null)
                    .withStart(new Point(2.23, 4.43))
                    .withEnd(new Point(2.23, 4.43))
                    .build());
        } catch (RoutingException ex) {
            assertThat(ex.getCause(), equalTo(exception));
            return;
        }
        fail("Exception not thrown");
    }
}