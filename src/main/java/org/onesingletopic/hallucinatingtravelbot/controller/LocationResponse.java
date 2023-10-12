package org.onesingletopic.hallucinatingtravelbot.controller;

import org.onesingletopic.hallucinatingtravelbot.repository.Location;

import java.util.List;

public record LocationResponse (List<Location> locations, String message) implements Response{

    @Override
    public String getMessage() {
        return message;
    }
}
