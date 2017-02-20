package org.mkhackathon.places.arttrail;

import de.micromata.opengis.kml.v_2_2_0.*;
import org.mkhackathon.places.PlaceOfInterest;
import org.mkhackathon.places.kml.FileReader;
import org.mkhackathon.places.kml.KmlReader;
import org.mkhackathon.routing.Point;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory class for building the ArtTrailProvider.
 * As we require loading the art trail KML file
 * it makes sense to do the logic here rather than
 * in a big constructor.
 */
@Configuration
class ArtTrailProviderFactory {

    @Bean
    public ArtTrailProvider artTrail() {
        final List<Placemark> placemarks = loadKmlPlacemarks();
        final List<PlaceOfInterest> placeOfInterests = placemarks.stream()
                .map(this::convertPlaceMark)
                .collect(Collectors.toList());
        return new ArtTrailProvider(placeOfInterests);
    }

    /**
     * This is a very horrible loader method. The KML library
     * requires lots of casting (yuck) and our file seems to
     * have many layers.
     * @return List of placemark objects
     */
    private List<Placemark> loadKmlPlacemarks() {
        final String kmlString = new FileReader("places/mk-public-art.kml").get();
        final Kml kml = new KmlReader(kmlString).get();
        final Document document = (Document) kml.getFeature();
        final ArrayList folder = (ArrayList) document.getFeature();
        Folder placemarksFolder = (Folder) folder.get(0);
        return (List<Placemark>) new ArrayList(placemarksFolder.getFeature());
    }

    private PlaceOfInterest convertPlaceMark(final Placemark placemark) {
        final de.micromata.opengis.kml.v_2_2_0.Point point =
                (de.micromata.opengis.kml.v_2_2_0.Point) placemark.getGeometry();
        final Coordinate coordinate = point.getCoordinates().get(0);

        return new PlaceOfInterest(placemark.getName(),
                new Point(coordinate.getLatitude(), coordinate.getLongitude()));
    }

}
