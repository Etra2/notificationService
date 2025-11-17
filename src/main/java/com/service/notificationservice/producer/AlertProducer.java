package com.service.notificationservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "alert-topic";

    public AlertProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAlert(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Alert sent: " + message);
    }
}