package com.sokolov.core.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.sokolov.core.domain.service.ShortUrlService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ShortUrlRedirectController {

    private final ShortUrlService shortUrlService;

    @GetMapping("/{shortUrl}")
    public RedirectView getUrl(@PathVariable String shortUrl) {
        String originalUrl = shortUrlService.retrieveOriginalUrl(shortUrl);
        return new RedirectView(originalUrl);
    }

}