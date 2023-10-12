package org.onesingletopic.hallucinatingtravelbot.controller;

import org.onesingletopic.hallucinatingtravelbot.repository.Location;

public record NewWordResponse(Location location, String message) implements Response{

    @Override
    public String getMessage() {
        return message;
    }
}
