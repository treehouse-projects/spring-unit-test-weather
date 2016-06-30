package com.teamtreehouse.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;

public class TimestampDeserializer  extends JsonDeserializer<Instant>{
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Long timestamp = p.getLongValue();
        return Instant.ofEpochSecond(timestamp);
    }
}
