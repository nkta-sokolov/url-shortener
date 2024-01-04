package com.sokolov.redirection_service.domain.service;

import org.springframework.stereotype.Service;

import com.sokolov.redirection_service.domain.model.ClickDetails;
import com.sokolov.redirection_service.util.HttpRequestUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ClickDetailsBuilder {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";

    public ClickDetails build(String targetUrl) {
        HttpServletRequest request = HttpRequestUtil.getCurrentRequest();

        if (request == null) {
            throw new IllegalStateException("There is no active request");
        }

        return buildClickDetails(request, targetUrl);
    }

    private ClickDetails buildClickDetails(HttpServletRequest request, String targetUrl) {
        String ip = buildIp(request);
        return ClickDetails.builder()
                .ip(ip)
                .userAgent(request.getHeader(USER_AGENT_HEADER_NAME))
                .targetUrl(targetUrl)
                .build();
    }

    private String buildIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("x-forwarded-for");
        if (forwardedFor == null) {
            return request.getRemoteAddr();
        }
        return forwardedFor;
    }

}