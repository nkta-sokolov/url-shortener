package com.sokolov.analytics_service.domain.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sokolov.analytics_service.domain.model.kafka.ClickDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClickDetailsConsumer {

    private final ClickDetailsService clickDetailsService;

    @KafkaListener(
            topics = "click-details",
            groupId = "analytics",
            containerFactory = "clickDetailsListenerContainerFactory"
    )
    public void receive(@Payload ClickDetails clickDetails) {
        log.debug("Received click details = {}", clickDetails);
        clickDetailsService.process(clickDetails);
    }

}