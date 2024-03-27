package com.sokolov.shorten_service.rest.model;

import jakarta.validation.constraints.NotBlank;

public record ShortUrlRequest(@NotBlank String targetUrl) {
}