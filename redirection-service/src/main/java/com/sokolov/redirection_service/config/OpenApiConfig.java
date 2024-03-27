package com.sokolov.redirection_service.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Redirection Service API", version = "v1"),
        servers = @Server(url = "${server.servlet.context-path}")
)
public class OpenApiConfig {
}