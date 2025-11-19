package com.service.notificationservice.controller;

import com.service.notificationservice.dto.MessageRequest;
import com.service.notificationservice.producer.MessageProducer;
import org.springframework.web.bind.annotation.*;

// Kontroler obsługujący wyłącznie "wiadomości" (MESSAGE)

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    // Odbiera JSON MessageRequest i wysyła go do Kafka
    // Przykład JSON:
    // {
    //   "content": "Serwis padł!",
    //   "type": "MESSAGE",
    //   "priority": "HIGH"
    //   }

    @PostMapping
    public String sendMessage(@RequestBody MessageRequest request) {
        producer.send(request);
        return "Message sent";
    }
}
