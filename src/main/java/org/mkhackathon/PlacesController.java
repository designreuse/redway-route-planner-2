package org.mkhackathon;

import org.mkhackathon.places.PlaceOfInterest;
import org.mkhackathon.places.PlaceOfInterestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlacesController {

    private final List<PlaceOfInterestProvider> providers;

    @Autowired
    public PlacesController(List<PlaceOfInterestProvider> providers) {
        this.providers = providers;
    }


    @RequestMapping(value = "places", method = RequestMethod.GET)
    public List<PlaceOfInterest> getPlaces() {
        // Loop over all our place of interest providers and combine the results
        return providers.stream()
                .map(provider -> provider.get())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }
}
