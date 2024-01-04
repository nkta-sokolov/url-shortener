package com.sokolov.redirection_service.domain.model;

import lombok.Builder;

@Builder
public record ClickDetails(String targetUrl, String ip, String userAgent) {
}