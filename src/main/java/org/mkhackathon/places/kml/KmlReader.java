package org.mkhackathon.places.kml;

import de.micromata.opengis.kml.v_2_2_0.Kml;

import java.io.File;

/**
 * KML is an XML based format for mapping data. It is used
 * heavily by Google. This class takes a String loaded (possibly
 * by the FileReader class and unmarshalls the string into a Kml
 * file.
 */
public final class KmlReader {

    private final String kml;

    public KmlReader(String kml) {
        this.kml = kml;
    }

    public Kml get() {
        return Kml.unmarshal(kml);
    }

}
