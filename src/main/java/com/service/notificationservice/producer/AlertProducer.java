package com.service.notificationservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate; // zmieniamy Object zamiast String
    private final String TOPIC = "alert-topic";

    public AlertProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAlert(String message) {
        // Możemy wysyłać String bez problemu – JsonSerializer obsłuży String
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Alert sent: " + message);
    }
}
