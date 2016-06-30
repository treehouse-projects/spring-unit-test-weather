package com.teamtreehouse.service.resttemplate;

import com.teamtreehouse.service.dto.geocoding.PlacesResult;

public interface PlacesService {
    PlacesResult findByPlaceId(String placeId);
}
