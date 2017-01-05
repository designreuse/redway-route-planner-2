package org.mkhackathon.routing.geocoding.osm;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NominationClientBuilder {

    @Value("${geocoder.email}")
    private String email;

    @Bean
    public NominatimClient client() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new JsonNominatimClient(httpClient, email);
    }
}
