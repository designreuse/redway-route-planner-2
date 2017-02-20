package org.mkhackathon.places.arttrail;

import org.junit.Before;
import org.junit.Test;
import org.mkhackathon.places.PlaceOfInterest;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ArtTrailProviderFactoryTest {

    private ArtTrailProviderFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new ArtTrailProviderFactory();
    }

    @Test
    public void should_construct_art_trail_from_file() throws Exception {
        ArtTrailProvider artTrailProvider = factory.artTrail();

        List<PlaceOfInterest> placeOfInterests = artTrailProvider.get();

        assertThat(placeOfInterests.get(0).getName(),
                equalTo("Peace Pagoda: Professor Minoru Ohka & Tom Hancock, 1980"));
    }
}