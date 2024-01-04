package com.sokolov.core.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.core.domain.exception.ShortUrlException;
import com.sokolov.core.domain.model.RequestDetails;
import com.sokolov.core.domain.model.UrlDetails;
import com.sokolov.core.persistence.model.UrlDetailsEntity;
import com.sokolov.core.persistence.repository.UrlDetailsRepository;
import com.sokolov.core.persistence.service.ShortUrlJpaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortUrlService {

    private final ShortUrlGenerator shortUrlGenerator;

    private final UrlDetailsRepository shortUrlRepository;

    private final ShortUrlJpaService shortUrlJpaService;

    private final KafkaStatisticProducer kafkaStatisticProducer;

    private final RequestDetailsBuilder requestDetailsBuilder;

    public UrlDetails shortenUrl(String originalUrl) {
        String shortUrl = retrieveShortUrl();
        return shortUrlJpaService.save(shortUrl, originalUrl);
    }

    public String retrieveOriginalUrl(String shortUrl) {
        String originalUrl = findOriginalUrlOrElseThrowException(shortUrl);
        RequestDetails requestDetails = requestDetailsBuilder.build(originalUrl);
        kafkaStatisticProducer.produce(requestDetails);
        return originalUrl;
    }

    private String retrieveShortUrl() {
        String shortUrl;
        do {
            shortUrl = shortUrlGenerator.generate();
        } while (shortUrlRepository.existsByShortUrl(shortUrl));
        return shortUrl;
    }

    private String findOriginalUrlOrElseThrowException(String shortUrl) {
        return shortUrlRepository.findByShortUrl(shortUrl)
                .map(UrlDetailsEntity::getOriginalUrl)
                .orElseThrow(() -> new ShortUrlException("Invalid short url"));
    }

}