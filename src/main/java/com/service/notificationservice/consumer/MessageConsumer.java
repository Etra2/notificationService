package com.service.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListener(topics = "message-topic", groupId = "message-group")
    public void listen(String message) {
        System.out.println("📥 Received message: " + message);
    }
}

