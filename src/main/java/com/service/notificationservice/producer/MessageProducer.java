package com.service.notificationservice.producer;

import com.service.notificationservice.dto.MessageRequest;
import com.service.notificationservice.model.MessageType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// Producent Kafka – wysyła wiadomości do odpowiednich topiców
// na podstawie typu wiadomości.

@Service
public class MessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String ALERT_TOPIC = "alert-topic";
    private static final String MESSAGE_TOPIC = "message-topic";

    public MessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Wysyła obiekt MessageRequest do Kafka.
    // Typ wiadomości decyduje, do którego topicu trafi.

    public void send(MessageRequest request) {

        // ENUM porównujemy przez == – bezpieczne dla enumów
        String topic = (request.getType() == MessageType.ALERT)
                ? ALERT_TOPIC
                : MESSAGE_TOPIC;

        kafkaTemplate.send(topic, request);

        System.out.println(
                "Wysłano wiadomość:" +
                        "\n treść: " + request.getContent() +
                        "\n typ: " + request.getType() +
                        "\n priorytet: " + request.getPriority() +
                        "\n topic: " + topic
        );
    }

    // Opcjonalna metoda, która umożliwia wywołanie z pojedynczymi parametrami.
    // Tworzy DTO i przekazuje do send().

    public void sendMessage(String content, MessageType type, com.service.notificationservice.model.MessagePriority priority) {
        MessageRequest request = new MessageRequest(content, type, priority);
        send(request);
    }
}
