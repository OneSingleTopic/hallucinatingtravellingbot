package org.onesingletopic.hallucinatingtravelbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.onesingletopic.hallucinatingtravelbot.repository.Location;
import org.onesingletopic.hallucinatingtravelbot.service.ChatRequest;
import org.onesingletopic.hallucinatingtravelbot.service.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class BotService {
    private LocationRepository repository;

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("$openai.model")
    String model;

    @Value("$openai.url")
    String apiUrl;

    private Deque<String> pre_locations = new ArrayDeque<String>(Arrays.asList(
            "{\"name\":\"Marienplatz\", \"lat_coord\":48.137150 ,\"lon_coord\":11.57539,\"description\":\"Où il fait bon vivre\"}",
            "{\"name\":\"Paris\",\"lat_coord\":48.866667, \"lon_coord\":2.333333, \"description\": \"Ville lumière\"}",
            "{\"name\":\"Toulouse\",\"lat_coord\":43.600000, \"lon_coord\":1.433333, \"description\": \"La ville rose\"}"
    ));

    @Autowired
    public BotService(LocationRepository repository) {
        this.repository = repository;
    }

    public Location generate_new_location(String newWord) {
        String chat_ai_response = call_chat_ai(newWord);
        Location new_location = parse_chat_ai_response(chat_ai_response);

        repository.save(new_location);

        return new_location;
    }

    public String call_chat_ai(String newWord){
//        String prompt = generate_prompt(newWord);
//        ChatRequest request = new ChatRequest(model, prompt);
//
//        // call the API
//        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
//
//        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
//            return null;
//        }
//
//        // return the first response
//        return response.getChoices().get(0).getMessage().getContent();

        return pre_locations.removeFirst();
    }

    public String generate_prompt(String new_word){
        return "''' " +
                System.getProperty("line.separator") +
                new_word +
                System.getProperty("line.separator") +
                " '''";
    }

    public Location parse_chat_ai_response(String chat_ai_response){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(chat_ai_response, Location.class);
        } catch (IOException e) {
            // Handle any exceptions, e.g., JSON parsing errors
            e.printStackTrace();
            return null;
        }
    }

    public List<Location> get_existing_locations() {
        Iterable<Location> existing_location_it = repository.findAll();
        List<Location> existing_locations = new ArrayList<Location>();
        existing_location_it.forEach(existing_locations::add);

        return existing_locations;
    }
}
