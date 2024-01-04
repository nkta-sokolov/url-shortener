package com.sokolov.shorten_service.rest.model;

import java.time.LocalDateTime;

public record ShortUrlResponse(String targetUrl, String shortUrl, LocalDateTime createdAt) {
}