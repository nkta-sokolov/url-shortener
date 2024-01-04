package com.sokolov.shorten_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI openAPI() {
        Info info = buildInfo();
        Server serverItem = buildServerItem();
        return new OpenAPI()
                .addServersItem(serverItem)
                .info(info);
    }

    private Info buildInfo() {
        return new Info()
                .title("Shorten Service API");
    }

    private Server buildServerItem() {
        return new Server()
                .url(contextPath);
    }

}