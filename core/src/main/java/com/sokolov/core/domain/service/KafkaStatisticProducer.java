package com.sokolov.core.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sokolov.core.domain.model.RequestDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaStatisticProducer {

    @Value("${statistic-producer.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, RequestDetails> kafkaTemplate;

    public void produce(RequestDetails requestDetails) {
        kafkaTemplate.send(topicName, requestDetails);
    }

}