package com.sokolov.analytics_service.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sokolov.analytics_service.domain.model.kafka.ClickDetails;

@Configuration
public class ClickDetailsConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClickDetails> clickDetailsListenerContainerFactory(
            ConsumerFactory<String, ClickDetails> clickDetailsConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, ClickDetails>();
        factory.setConsumerFactory(clickDetailsConsumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ClickDetails> clickDetailsConsumerFactory(KafkaProperties kafkaProperties,
                                                                             SslBundles sslBundles,
                                                                             Deserializer<ClickDetails> clickDetailsDeserializer) {
        Map<String, Object> properties = buildProperties(kafkaProperties, sslBundles);
        var factory = new DefaultKafkaConsumerFactory<String, ClickDetails>(properties);
        factory.setValueDeserializer(clickDetailsDeserializer);
        return factory;
    }

    private Map<String, Object> buildProperties(KafkaProperties kafkaProperties, SslBundles sslBundles) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties(sslBundles);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return properties;
    }

    @Bean
    public Deserializer<ClickDetails> clickDetailsDeserializer(ObjectMapper objectMapper) {
        JsonDeserializer<ClickDetails> deserializer = new JsonDeserializer<>(ClickDetails.class, objectMapper);
        deserializer.setUseTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        return new ErrorHandlingDeserializer<>(deserializer);
    }

}