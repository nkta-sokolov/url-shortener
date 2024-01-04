package com.sokolov.redirection_service.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.redirection_service.domain.exception.RedirectionServiceException;
import com.sokolov.redirection_service.domain.model.ClickDetails;
import com.sokolov.redirection_service.persistence.model.UrlDetailsEntity;
import com.sokolov.redirection_service.persistence.repository.UrlDetailsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedirectionService {

    private final UrlDetailsRepository urlDetailsRepository;

    private final ClickDetailsBuilder clickDetailsBuilder;

    private final KafkaClickDetailsProducer kafkaClickDetailsProducer;

    public String retrieveTargetUrl(String shortUrl) {
        String targetUrl = findTargetUrlOrElseThrowException(shortUrl);
        ClickDetails clickDetails = clickDetailsBuilder.build(targetUrl);
        kafkaClickDetailsProducer.produce(clickDetails);
        return targetUrl;
    }

    private String findTargetUrlOrElseThrowException(String shortUrl) {
        return urlDetailsRepository.findByShortUrl(shortUrl)
                .map(UrlDetailsEntity::getTargetUrl)
                .orElseThrow(() -> new RedirectionServiceException("Invalid short url"));
    }

}