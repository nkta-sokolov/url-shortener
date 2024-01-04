package com.sokolov.core.persistence.service;

import org.springframework.stereotype.Service;

import com.sokolov.core.domain.model.UrlDetails;
import com.sokolov.core.mapper.ShortUrlMapper;
import com.sokolov.core.persistence.model.UrlDetailsEntity;
import com.sokolov.core.persistence.repository.UrlDetailsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortUrlJpaService {

    private final UrlDetailsRepository shortUrlRepository;

    private final ShortUrlMapper shortUrlMapper;

    public UrlDetails save(String shortUrl, String originalUrl) {
        UrlDetailsEntity urlDetailsEntity = shortUrlMapper.domainToData(originalUrl, shortUrl);
        UrlDetailsEntity savedUrlDetails = shortUrlRepository.save(urlDetailsEntity);
        return shortUrlMapper.dataToDomain(savedUrlDetails);
    }

}