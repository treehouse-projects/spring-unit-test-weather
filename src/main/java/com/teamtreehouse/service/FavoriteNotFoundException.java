package com.teamtreehouse.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException() {
        super("Favorite not found.");
    }
}
