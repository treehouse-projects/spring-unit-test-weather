package com.teamtreehouse.service;

import com.teamtreehouse.service.dto.geocoding.Location;
import com.teamtreehouse.service.dto.weather.Weather;

public interface WeatherService {
    Weather findByLocation(Location location);
}