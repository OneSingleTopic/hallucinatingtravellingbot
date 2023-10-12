package org.onesingletopic.hallucinatingtravelbot.service;

import java.util.List;
import java.util.Map;

public record ChatResponse (List<Choice> choices){

    public List<Choice> getChoices() {
        return choices;
    }

    public record Choice (int index, Message message){
        public Message getMessage() {
            return message;
        }
    }
}