package org.onesingletopic.hallucinatingtravelbot;

import org.onesingletopic.hallucinatingtravelbot.controller.ErrorResponse;
import org.onesingletopic.hallucinatingtravelbot.controller.LocationResponse;
import org.onesingletopic.hallucinatingtravelbot.controller.NewWordResponse;
import org.onesingletopic.hallucinatingtravelbot.controller.Response;

import org.onesingletopic.hallucinatingtravelbot.repository.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BotController {
    private BotService service;

    @Autowired
    public BotController(BotService botService) {
        this.service = botService;
    }

    @GetMapping("/new_location")
    public ResponseEntity<Response> get_new_location(@RequestParam(name="new_word", required = true) String new_word){
        try {
            Location new_location = service.generate_new_location(new_word);
            return ResponseEntity.ok().body(
                    new NewWordResponse(new_location, "Success"));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(
                    new ErrorResponse(ex.getMessage()));
        }
    }
    @GetMapping("/locations")
    public ResponseEntity<Response> get_existing_locations(){
        System.out.println("coucou");
        try {
            List<Location> existing_locations = service.get_existing_locations();
            return ResponseEntity.ok().body(
                    new LocationResponse(existing_locations, "Success"));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(
                    new ErrorResponse(ex.getMessage()));
        }
    }
}
