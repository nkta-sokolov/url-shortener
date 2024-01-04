package com.sokolov.core.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDetails {

    private String originalUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

}