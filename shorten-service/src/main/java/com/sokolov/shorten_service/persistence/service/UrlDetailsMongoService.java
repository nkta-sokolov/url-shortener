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

    public UrlDetails save(String shortUrlIdentifier, String targetUrl) {
        UrlDetailsEntity urlDetailsEntity = urlDetailsMapper.domainToData(targetUrl, shortUrlIdentifier);
        UrlDetailsEntity savedUrlDetails = urlDetailsRepository.save(urlDetailsEntity);
        return urlDetailsMapper.dataToDomain(savedUrlDetails);
    }

    public boolean existsByShortUrlIdentifier(String shortUrlIdentifier) {
        return urlDetailsRepository.existsByShortUrlIdentifier(shortUrlIdentifier);
    }

}