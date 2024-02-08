package com.sokolov.redirection_service.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sokolov.redirection_service.domain.model.ClickDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaClickDetailsProducer {

    @Value("${producer.click-details.topic-name}")
    private final String topicName;

    private final KafkaTemplate<String, ClickDetails> kafkaTemplate;

    public void produce(ClickDetails clickDetails) {
        kafkaTemplate.send(topicName, clickDetails);
    }

}