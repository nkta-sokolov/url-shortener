package com.sokolov.core.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sokolov.core.domain.model.UrlDetails;
import com.sokolov.core.persistence.model.UrlDetailsEntity;

public interface UrlDetailsRepository extends JpaRepository<UrlDetailsEntity, Long> {

    boolean existsByShortUrl(String shortUrl);

    Optional<UrlDetailsEntity> findByShortUrl(String shortUrl);

}