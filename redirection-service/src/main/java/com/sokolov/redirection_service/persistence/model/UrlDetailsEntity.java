package com.sokolov.redirection_service.persistence.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("url_details")
public class UrlDetailsEntity {

    @Id
    private String id;

    private String targetUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

}