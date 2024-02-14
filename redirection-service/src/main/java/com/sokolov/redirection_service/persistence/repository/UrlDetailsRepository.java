package com.sokolov.redirection_service.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sokolov.redirection_service.persistence.model.UrlDetailsEntity;

public interface UrlDetailsRepository extends MongoRepository<UrlDetailsEntity, String> {

    Optional<UrlDetailsEntity> findByShortUrlIdentifier(String shortUrl);

}