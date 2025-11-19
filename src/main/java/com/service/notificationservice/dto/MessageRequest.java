package com.service.notificationservice.dto;

import com.service.notificationservice.model.MessagePriority;
import com.service.notificationservice.model.MessageType;

/**
 * DTO odbierane z REST – zawiera treść wiadomości, jej typ (enum MessageType)
 * oraz priorytet (enum MessagePriority).
 *
 * Uwaga: importy muszą wskazywać na ten sam pakiet, w którym zdefiniowane są enumy:
 * com.service.notificationservice.model
 */
public class MessageRequest {
    private String content;              // treść wiadomości
    private MessageType type;            // ALERT / MESSAGE
    private MessagePriority priority;    // HIGH / LOW

    public MessageRequest() {}

    public MessageRequest(String content, MessageType type, MessagePriority priority) {
        this.content = content;
        this.type = type;
        this.priority = priority;
    }

    // GETTERY I SETTERY
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }
}
