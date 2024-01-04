package com.sokolov.core.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.core.domain.model.RequestDetails;
import com.sokolov.core.util.HttpRequestUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestDetailsBuilder {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";

    public RequestDetails build(String originalUrl) {
        HttpServletRequest request = HttpRequestUtil.getCurrentRequest();

        if (request == null) {
            //todo:
            throw new RuntimeException("There is no active request");
        }

        return buidRequestDetails(request, originalUrl);
    }

    private RequestDetails buidRequestDetails(HttpServletRequest request, String originalUrl) {
        return RequestDetails.builder()
                .ip(request.getRemoteAddr())
                .userAgent(request.getHeader(USER_AGENT_HEADER_NAME))
                .originalUrl(originalUrl)
                .build();
    }

}