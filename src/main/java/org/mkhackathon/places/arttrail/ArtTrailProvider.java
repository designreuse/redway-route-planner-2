package org.mkhackathon.places.arttrail;

import org.mkhackathon.places.PlaceOfInterest;
import org.mkhackathon.places.PlaceOfInterestProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides a list of places of interest for public
 * art pieces around Milton Keynes.
 *
 * This Bean is created by the ArtTrailProviderFactory
 */
public final class ArtTrailProvider implements PlaceOfInterestProvider {

    private final List<PlaceOfInterest> artTrail;

    public ArtTrailProvider(List<PlaceOfInterest> artTrail) {
        this.artTrail = artTrail;
    }

    @Override
    public List<PlaceOfInterest> get() {
        return artTrail;
    }
}
