package com.sokolov.redirection_service.domain.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import com.sokolov.redirection_service.domain.exception.RedirectionServiceException;
import com.sokolov.redirection_service.persistence.model.UrlDetailsEntity;
import com.sokolov.redirection_service.persistence.repository.UrlDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ManagedResource(objectName = "com.sokolov.redirection_service.domain.service:type=service, name=UrlDetailsService")
@Service
public class UrlDetailsService {

    public static final String CACHE_NAME = "target_urls";

    private final UrlDetailsRepository urlDetailsRepository;

    @Cacheable(CACHE_NAME)
    public String findTargetUrlOrElseThrowException(String shortUrl) {
        return urlDetailsRepository.findByShortUrlIdentifier(shortUrl)
                .map(UrlDetailsEntity::getTargetUrl)
                .orElseThrow(() -> new RedirectionServiceException("Invalid short url"));
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @ManagedOperation
    public void evictCache() {
        log.debug("Evicted {} cache", CACHE_NAME);
    }

}