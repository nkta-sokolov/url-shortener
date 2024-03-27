package com.sokolov.shorten_service.rest.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sokolov.shorten_service.domain.model.UrlDetails;
import com.sokolov.shorten_service.domain.service.ShortUrlService;
import com.sokolov.shorten_service.rest.model.ShortUrlRequest;
import com.sokolov.shorten_service.rest.model.ShortUrlResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/short-urls")
@RestController
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ShortUrlResponse shortenUrl(@RequestBody @Valid ShortUrlRequest request) {
        UrlDetails urlDetails = shortUrlService.shortenUrl(request.targetUrl());
        return new ShortUrlResponse(urlDetails.getTargetUrl(), urlDetails.getShortUrlIdentifier(), urlDetails.getCreatedAt());
    }

}