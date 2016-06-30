package com.teamtreehouse.service.resttemplate.geocoding;

import com.teamtreehouse.service.GeocodingService;
import com.teamtreehouse.service.dto.geocoding.GeocodingResponse;
import com.teamtreehouse.service.dto.geocoding.GeocodingResult;
import com.teamtreehouse.service.resttemplate.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("api.properties")
public class GeocodingServiceImpl extends RestApiService<GeocodingResponse> implements GeocodingService {
    private String name;
    private String key;
    private String host;
    private String region;

    @Autowired
    public GeocodingServiceImpl(
            @Value("${geocode.api.name}") String name,
            @Value("${geocode.api.key}") String key,
            @Value("${geocode.api.host}") String host,
            @Value("${geocode.api.region}") String region) {
        super();
        this.name = name;
        this.key = key;
        this.host = host;
        this.region = region;
    }

    @Override
    public GeocodingResult findBySearchTerm(String q) {
        GeocodingResponse response = get("/maps/api/geocode/json?address={q}&sensor=false&key={key}")
                .param("q",q)
                .execute();
        return response.getResults().get(0);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getApiKey() {
        return key;
    }

    @Override
    public Class<GeocodingResponse> getDtoClass() {
        return GeocodingResponse.class;
    }
}
