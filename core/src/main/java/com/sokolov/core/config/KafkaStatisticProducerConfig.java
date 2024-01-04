package com.sokolov.core.config;

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
import com.sokolov.core.domain.model.RequestDetails;

@Configuration
public class KafkaStatisticProducerConfig {

    @Bean
    public KafkaTemplate<String, RequestDetails> statistickafkaTemplate(ProducerFactory<String, RequestDetails> statisticProducerFactory) {
        return new KafkaTemplate<>(statisticProducerFactory);
    }

    @Bean
    public ProducerFactory<String, RequestDetails> statisticProducerFactory(JsonSerializer<RequestDetails> kafkaJsonSerializer,
                                                                            KafkaProperties kafkaProperties, SslBundles sslBundles) {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties(sslBundles);
        DefaultKafkaProducerFactory<String, RequestDetails> factory = new DefaultKafkaProducerFactory<>(properties);
        factory.setValueSerializer(kafkaJsonSerializer);
        return factory;
    }

    @Bean
    public JsonSerializer<RequestDetails> kafkaJsonSerializer(ObjectMapper objectMapper) {
        return new JsonSerializer<>(objectMapper);
    }

}