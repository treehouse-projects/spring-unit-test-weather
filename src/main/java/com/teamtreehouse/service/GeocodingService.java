package com.teamtreehouse.service;

import com.teamtreehouse.service.dto.geocoding.GeocodingResult;

public interface GeocodingService {
    GeocodingResult findBySearchTerm(String q);
}