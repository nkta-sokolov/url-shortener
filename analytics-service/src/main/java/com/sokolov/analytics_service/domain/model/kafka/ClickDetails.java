package com.sokolov.analytics_service.domain.model.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClickDetails {

    private String targetUrl;

    private String userAgent;

    private String ip;

}