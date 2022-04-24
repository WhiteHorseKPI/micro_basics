package com.microservices.facadeservice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RestController
public class FacadeController {

    WebClient loggingWebClient = WebClient.create("http://localhost:8889");
    WebClient messagesWebClient = WebClient.create("http://localhost:8890");


    @GetMapping("/facade_service")
    public String clientWebClient() {

        var cachedValues = loggingWebClient
                .get()
                .uri("/log")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        var message = messagesWebClient
                .get()
                .uri("/message")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return String.format("%s: %s", cachedValues, message);
    }


    @PostMapping("/facade_service")
    public Mono<Void> facadeWebClient(@RequestBody String text) {

        Map<String, Object> message = new HashMap<>();
        message.put("UUID", UUID.randomUUID());
        message.put("Text", text);

        return loggingWebClient.post()
                .uri("/log")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(message), Map.class)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
