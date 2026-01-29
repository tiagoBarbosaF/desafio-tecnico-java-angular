package com.tiagobarbosa.backend.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCreditoEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaCreditoEventPublisher.class);
    private static final String TOPIC_NAME = "consulta_credito";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ConsultaCreditoEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(ConsultaCreditoEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC_NAME, payload);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao serializar consulta de crédito para Kafka: {}", event, e);
        } catch (Exception e) {
            logger.error("Erro ao publicar evento de consulta de crédito no Kafka", e);
        }
    }
}
