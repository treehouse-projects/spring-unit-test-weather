package com.teamtreehouse.service.resttemplate.geocoding;

import com.teamtreehouse.service.dto.geocoding.PlacesResponse;
import com.teamtreehouse.service.dto.geocoding.PlacesResult;
import com.teamtreehouse.service.resttemplate.PlacesService;
import com.teamtreehouse.service.resttemplate.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("api.properties")
public class PlacesServiceImpl extends RestApiService<PlacesResponse> implements PlacesService {
    private String name;
    private String key;
    private String host;

    @Autowired
    public PlacesServiceImpl(
            @Value("${places.api.name}") String name,
            @Value("${places.api.key}") String key,
            @Value("${places.api.host}") String host) {
        super();
        this.name = name;
        this.key = key;
        this.host = host;
    }

    @Override
    public PlacesResult findByPlaceId(String placeId) {
        PlacesResponse response = get("/maps/api/place/details/json?placeid={placeId}&sensor=false&key={key}")
                .param("placeId",placeId)
                .execute();
        return response.getResult();
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
    public Class<PlacesResponse> getDtoClass() {
        return PlacesResponse.class;
    }
}
