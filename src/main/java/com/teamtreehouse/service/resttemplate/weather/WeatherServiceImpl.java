package com.teamtreehouse.service.resttemplate.weather;

import com.teamtreehouse.service.WeatherService;
import com.teamtreehouse.service.dto.geocoding.Location;
import com.teamtreehouse.service.dto.weather.Weather;
import com.teamtreehouse.service.resttemplate.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("api.properties")
public class WeatherServiceImpl extends RestApiService<Weather> implements WeatherService {

    private String name;
    private String key;
    private String host;

    @Autowired
    public WeatherServiceImpl(
            @Value("${weather.api.name}") String name,
            @Value("${weather.api.key}") String key,
            @Value("${weather.api.host}") String host) {
        super();
        this.name = name;
        this.key = key;
        this.host = host;
    }

    @Override
    public Weather findByLocation(Location location) {
        return get("/forecast/{key}/{lat},{lng}")
                .param("lat",location.getLatitude())
                .param("lng",location.getLongitude())
                .execute();
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
    public Class<Weather> getDtoClass() {
        return Weather.class;
    }
}