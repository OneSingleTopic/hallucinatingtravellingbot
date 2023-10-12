package org.onesingletopic.hallucinatingtravelbot.service;

import org.onesingletopic.hallucinatingtravelbot.repository.Location;

public record Message (String role, String content){
    public String getContent() {
        return content;
    }
}