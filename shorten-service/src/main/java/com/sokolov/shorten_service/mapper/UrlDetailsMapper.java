package com.sokolov.shorten_service.mapper;

import org.mapstruct.Mapper;

import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.persistence.model.UrlDetailsEntity;

@Mapper(componentModel = "spring")
public interface UrlDetailsMapper {

    UrlDetailsEntity domainToEntity(String targetUrl, String shortUrlIdentifier);

    UrlDetails entityToDomain(UrlDetailsEntity urlDetailsEntity);

}