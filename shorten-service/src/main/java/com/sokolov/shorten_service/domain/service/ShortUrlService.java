package com.sokolov.shorten_service.domain.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.sokolov.shorten_service.client.IdGeneratorServiceClient;
import com.sokolov.shorten_service.domain.exception.ShortenServiceException;
import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.persistence.service.UrlDetailsMongoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortUrlService {

    private final UrlDetailsMongoService shortUrlMongoService;

    private final IdGeneratorServiceClient idGeneratorServiceClient;

    public UrlDetails shortenUrl(String targetUrl) {
        String shortUrlIdentifier = generateShortUrlIdentifier(targetUrl);
        return shortUrlMongoService.save(shortUrlIdentifier, targetUrl);
    }

    private String generateShortUrlIdentifier(String targetUrl) {
        return Stream.generate(() -> idGeneratorServiceClient.generateId().identifier())
                .dropWhile(shortUrlMongoService::existsByShortUrlIdentifier)
                .findFirst()
                .orElseThrow(() -> new ShortenServiceException("Can't generate short url identifier for target url = " + targetUrl));
    }

}