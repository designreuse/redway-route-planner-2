package org.mkhackathon.places;

import java.util.List;

/**
 * A generic interface for providing places of interest.
 * Any beans implementing this interface will be automatically
 * injected into the PlacesController and their places of interest
 * will be served up.
 */
public interface PlaceOfInterestProvider {

    List<PlaceOfInterest> get();


}
