package com.sokolov.shorten_service.domain.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlGenerator {

    @Value("${short-url.length}")
    private Integer shortUrlLength;

    public String generate() {
        return RandomStringUtils.randomAlphabetic(shortUrlLength);
    }

}