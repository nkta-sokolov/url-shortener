package com.sokolov.id_generator_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sokolov.id_generator_service.domain.service.NodeIdBuilder;

@Configuration
public class NodeIdConfig {

    @Bean
    public Integer nodeId(NodeIdBuilder nodeIdBuilder) {
        return nodeIdBuilder.build();
    }

}