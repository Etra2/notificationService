package com.service.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AlertConsumer {

    @KafkaListener(topics = "alert-topic", groupId = "alert-group")
    public void listen(String message) {
        System.out.println("📥 Received alert: " + message);
    }
}
