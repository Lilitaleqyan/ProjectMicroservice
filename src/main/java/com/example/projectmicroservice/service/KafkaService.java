package com.example.projectmicroservice.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMessage() {
        kafkaTemplate.send("payment-created-topic", "Hello, this is my first message");
        System.out.println("Message ok!");
    }

    @KafkaListener(topics = "payment-created-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Message: " + message);
    }
}
