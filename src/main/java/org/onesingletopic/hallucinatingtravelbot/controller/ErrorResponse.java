package org.onesingletopic.hallucinatingtravelbot.controller;

public record ErrorResponse(String message) implements Response{

    @Override
    public String getMessage() {
        return message;
    }
}
