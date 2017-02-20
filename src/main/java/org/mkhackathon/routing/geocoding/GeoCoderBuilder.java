package org.mkhackathon.routing.geocoding;

import com.google.maps.GeoApiContext;
import fr.dudie.nominatim.client.JsonNominatimClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mkhackathon.routing.geocoding.google.GoogleGeoCoder;
import org.mkhackathon.routing.geocoding.osm.OsmGeoCoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoCoderBuilder {

    @Value("${geocoder.google.apiKey}")
    private String googleApiKey;

    @Value("${geocoder.email}")
    private String email;

    @Bean
    public GeoCoder getGeoCoder() {
        if (!"x".equals(googleApiKey)) {
            return buildGoogleGeoCoder();
        }
        return buildOsmGeoCoder();
    }

    private GeoCoder buildOsmGeoCoder() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        JsonNominatimClient nominatimClient = new JsonNominatimClient(httpClient, email);
        return new OsmGeoCoder(nominatimClient);
    }

    private GeoCoder buildGoogleGeoCoder() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey(googleApiKey);
        return new GoogleGeoCoder(geoApiContext);
    }
}
