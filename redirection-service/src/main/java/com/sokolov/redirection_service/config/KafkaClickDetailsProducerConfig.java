package com.sokolov.redirection_service.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sokolov.redirection_service.domain.model.ClickDetails;

@Configuration
public class KafkaClickDetailsProducerConfig {

    @Bean
    public KafkaTemplate<String, ClickDetails> clickDetailsKafkaTemplate(ProducerFactory<String, ClickDetails> clickDetailsProducerFactory) {
        return new KafkaTemplate<>(clickDetailsProducerFactory);
    }

    @Bean
    public ProducerFactory<String, ClickDetails> clickDetailsProducerFactory(JsonSerializer<ClickDetails> clickDetailsJsonSerializer,
                                                                             KafkaProperties kafkaProperties, SslBundles sslBundles) {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties(sslBundles);
        DefaultKafkaProducerFactory<String, ClickDetails> factory = new DefaultKafkaProducerFactory<>(properties);
        factory.setValueSerializer(clickDetailsJsonSerializer);
        return factory;
    }

    @Bean
    public JsonSerializer<ClickDetails> clickDetailsJsonSerializer(ObjectMapper objectMapper) {
        return new JsonSerializer<>(objectMapper);
    }

}