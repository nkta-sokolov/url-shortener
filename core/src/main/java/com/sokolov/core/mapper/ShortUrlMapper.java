package com.sokolov.core.mapper;

import org.mapstruct.Mapper;

import com.sokolov.core.domain.model.UrlDetails;
import com.sokolov.core.persistence.model.UrlDetailsEntity;

@Mapper(componentModel = "spring")
public interface ShortUrlMapper {

    default UrlDetailsEntity domainToData(String originalUrl, String shortUrl){
        UrlDetailsEntity urlDetailsEntity = new UrlDetailsEntity();
        urlDetailsEntity.setOriginalUrl(originalUrl);
        urlDetailsEntity.setShortUrl(shortUrl);
        return urlDetailsEntity;
    }

    UrlDetails dataToDomain(UrlDetailsEntity urlDetailsEntity);

}