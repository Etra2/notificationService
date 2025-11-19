package com.service.notificationservice.controller;

import com.service.notificationservice.model.MessagePriority;
import com.service.notificationservice.model.MessageType;
import com.service.notificationservice.producer.AlertProducer;
import com.service.notificationservice.producer.MessageProducer;
import com.service.notificationservice.dto.MessageRequest;
import org.springframework.web.bind.annotation.*;

// Kontroler obsługujący alerty (ALERT) oraz dodatkowo umożliwia
// wysłanie wiadomości z typem i priorytetem.

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    private final AlertProducer alertProducer;
    private final MessageProducer messageProducer;

    public AlertController(AlertProducer alertProducer, MessageProducer messageProducer) {
        this.alertProducer = alertProducer;
        this.messageProducer = messageProducer;
    }

    //  Wysyła prosty alert (tylko treść) do topicu alertowego.

    @PostMapping("/sendAlert")
    public String sendAlert(@RequestParam String msg) {
        alertProducer.sendAlert(msg);
        return "Alert sent: " + msg;
    }

    // Wysyła wiadomość z typem i priorytetem (do topicu MESSAGE lub ALERT zależnie od type).

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody MessageRequest request) {
        // używa metody sendMessage w producencie, żeby było zgodne z sygnaturą
        messageProducer.sendMessage(
                request.getContent(),
                request.getType(),
                request.getPriority()
        );

        return "Message sent: " + request.getContent() +
                " | type: " + request.getType() +
                " | priority: " + request.getPriority();
    }
}
