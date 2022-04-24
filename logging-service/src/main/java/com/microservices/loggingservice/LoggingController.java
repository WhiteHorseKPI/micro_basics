package com.microservices.loggingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
    private final Map<String, String> messages = new ConcurrentHashMap<>();


    @GetMapping("/log")
    public String listLog() {
        return messages.values().toString();
    }


    @PostMapping("/log")
    public ResponseEntity<Void> log(@RequestBody Map<String, Object> msg) {

        final String id = (String) msg.get("UUID");
        final String text = (String) msg.get("Text");

        logger.info("UUID: " + id);
        logger.info("Text: " + text);
        messages.put(id, text);
        return ResponseEntity.ok().build();
    }
}
