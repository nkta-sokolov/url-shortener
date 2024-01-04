package com.sokolov.redirection_service.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.sokolov.redirection_service.domain.service.RedirectionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RedirectionController {

    private final RedirectionService redirectionService;

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToTargetUrl(@PathVariable String shortUrl) {
        String targetUrl = redirectionService.retrieveTargetUrl(shortUrl);
        return new RedirectView(targetUrl);
    }

}