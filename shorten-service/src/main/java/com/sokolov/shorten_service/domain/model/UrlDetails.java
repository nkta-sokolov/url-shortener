package com.sokolov.shorten_service.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDetails {

    private String targetUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

}