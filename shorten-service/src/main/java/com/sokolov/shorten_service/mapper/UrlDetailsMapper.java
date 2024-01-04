package com.sokolov.shorten_service.mapper;

import org.mapstruct.Mapper;

import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.persistence.model.UrlDetailsEntity;

@Mapper(componentModel = "spring")
public interface UrlDetailsMapper {

    default UrlDetailsEntity domainToData(String targetUrl, String shortUrl){
        UrlDetailsEntity urlDetailsEntity = new UrlDetailsEntity();
        urlDetailsEntity.setTargetUrl(targetUrl);
        urlDetailsEntity.setShortUrl(shortUrl);
        return urlDetailsEntity;
    }

    UrlDetails dataToDomain(UrlDetailsEntity urlDetailsEntity);

}