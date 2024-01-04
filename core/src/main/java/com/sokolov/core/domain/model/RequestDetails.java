package com.sokolov.core.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestDetails {

    private String originalUrl;

    private String userAgent;

    private String ip;

}