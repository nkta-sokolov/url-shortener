package com.sokolov.shorten_service.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sokolov.shorten_service.persistence.model.UrlDetailsEntity;

public interface UrlDetailsRepository extends MongoRepository<UrlDetailsEntity, String> {

    boolean existsByShortUrl(String shortUrl);

}