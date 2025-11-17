package com.service.notificationservice.controller;

import com.service.notificationservice.producer.AlertProducer;
import com.service.notificationservice.producer.MessageProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    private final AlertProducer alertProducer;
    private final MessageProducer messageProducer;

    public AlertController(AlertProducer alertProducer, MessageProducer messageProducer) {
        this.alertProducer = alertProducer;
        this.messageProducer = messageProducer;
    }

    @PostMapping("/sendAlert")
    public String sendAlert(@RequestParam String msg) {
        alertProducer.sendAlert(msg);
        return "Alert sent: " + msg;
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String msg) {
        messageProducer.sendMessage(msg);
        return "Message sent: " + msg;
    }
}
