package com.sokolov.shorten_service.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.shorten_service.client.IdGeneratorServiceClient;
import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.persistence.service.UrlDetailsMongoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortUrlService {

    private final UrlDetailsMongoService shortUrlMongoService;

    private final IdGeneratorServiceClient idGeneratorServiceClient;

    public UrlDetails shortenUrl(String targetUrl) {
        String shortUrlIdentifier = generateShortUrlIdentifier();
        return shortUrlMongoService.save(shortUrlIdentifier, targetUrl);
    }

    private String generateShortUrlIdentifier() {
        String shortUrlIdentifier;
        do {
            shortUrlIdentifier = idGeneratorServiceClient.generateId().identifier();
        } while (shortUrlMongoService.existsByShortUrlIdentifier(shortUrlIdentifier));
        return shortUrlIdentifier;
    }

}