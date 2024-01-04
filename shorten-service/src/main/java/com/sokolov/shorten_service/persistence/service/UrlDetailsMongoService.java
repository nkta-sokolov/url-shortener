package com.sokolov.shorten_service.persistence.service;

import org.springframework.stereotype.Service;

import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.mapper.UrlDetailsMapper;
import com.sokolov.shorten_service.persistence.model.UrlDetailsEntity;
import com.sokolov.shorten_service.persistence.repository.UrlDetailsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UrlDetailsMongoService {

    private final UrlDetailsRepository urlDetailsRepository;

    private final UrlDetailsMapper urlDetailsMapper;

    public UrlDetails save(String shortUrl, String targetUrl) {
        UrlDetailsEntity urlDetailsEntity = urlDetailsMapper.domainToData(targetUrl, shortUrl);
        UrlDetailsEntity savedUrlDetails = urlDetailsRepository.save(urlDetailsEntity);
        return urlDetailsMapper.dataToDomain(savedUrlDetails);
    }

    public boolean existsByShortUrl(String shortUrl) {
        return urlDetailsRepository.existsByShortUrl(shortUrl);
    }

}