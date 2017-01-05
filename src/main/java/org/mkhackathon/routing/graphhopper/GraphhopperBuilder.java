package org.mkhackathon.routing.graphhopper;

import com.graphhopper.api.GraphHopperWeb;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GraphhopperBuilder {

    @Value("${graphhopper.routingUrl}")
    private String graphhopperRoutingUrl;

    @Bean
    public GraphHopperWeb graphHopperWeb() {
        GraphHopperWeb graphHopperWeb = new GraphHopperWeb(graphhopperRoutingUrl);
        graphHopperWeb.setDownloader(new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).build());
        return graphHopperWeb;
    }
}
