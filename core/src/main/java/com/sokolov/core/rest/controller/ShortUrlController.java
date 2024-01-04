package com.sokolov.core.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sokolov.core.domain.model.UrlDetails;
import com.sokolov.core.domain.service.ShortUrlService;
import com.sokolov.core.rest.model.ShortUrlRequest;
import com.sokolov.core.rest.model.ShortUrlResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/short-urls")
@RestController
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ShortUrlResponse shortenUrl(@RequestBody ShortUrlRequest request) {
        UrlDetails urlDetails = shortUrlService.shortenUrl(request.originalUrl());
        return new ShortUrlResponse(urlDetails.getOriginalUrl(), urlDetails.getShortUrl());
    }

}