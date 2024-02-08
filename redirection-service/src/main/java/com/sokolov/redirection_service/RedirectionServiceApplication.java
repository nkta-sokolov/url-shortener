package com.sokolov.redirection_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedirectionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedirectionServiceApplication.class, args);
    }

}