package com.sokolov.redirection_service.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.redirection_service.domain.model.ClickDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedirectionService {

    private final ClickDetailsBuilder clickDetailsBuilder;

    private final KafkaClickDetailsProducer kafkaClickDetailsProducer;

    private final UrlDetailsService urlDetailsService;

    public String retrieveTargetUrl(String shortUrl) {
        String targetUrl = urlDetailsService.findTargetUrlOrElseThrowException(shortUrl);
        ClickDetails clickDetails = clickDetailsBuilder.build(targetUrl);
        kafkaClickDetailsProducer.produce(clickDetails);
        return targetUrl;
    }

}