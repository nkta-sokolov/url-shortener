package com.sokolov.shorten_service.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.persistence.service.UrlDetailsMongoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortUrlService {

    private final ShortUrlGenerator shortUrlGenerator;

    private final UrlDetailsMongoService shortUrlMongoService;

    public UrlDetails shortenUrl(String targetUrl) {
        String shortUrl = generateShortUrl();
        return shortUrlMongoService.save(shortUrl, targetUrl);
    }

    private String generateShortUrl() {
        String shortUrl;
        do {
            shortUrl = shortUrlGenerator.generate();
        } while (shortUrlMongoService.existsByShortUrl(shortUrl));
        return shortUrl;
    }

}