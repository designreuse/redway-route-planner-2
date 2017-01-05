package org.mkhackathon;

import org.mkhackathon.routing.GeoCodedRoutingRequest;
import org.mkhackathon.routing.Router;
import org.mkhackathon.routing.RoutingRequest;
import org.mkhackathon.routing.RoutingResponse;
import org.mkhackathon.routing.geocoding.GeoCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RoutingController {

    private final GeoCoder geoCoder;
    private final Router router;

    @Autowired
    public RoutingController(final GeoCoder geoCoder, final Router router) {
        this.geoCoder = geoCoder;
        this.router = router;
    }

    @RequestMapping(value = "routing", method = RequestMethod.POST)
    public RoutingResponse findRoute(@RequestBody final RoutingRequest routingRequest) {
        final GeoCodedRoutingRequest geoCodedRoutingRequest = geoCoder.geoCode(routingRequest);
        return router.getRoute(geoCodedRoutingRequest);
    }

}
