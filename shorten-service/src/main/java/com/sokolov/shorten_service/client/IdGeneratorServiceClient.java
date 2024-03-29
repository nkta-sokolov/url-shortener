package com.sokolov.shorten_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sokolov.shorten_service.client.model.IdResponse;

@FeignClient(
        name = "idGeneratorServiceClient",
        url = "${api.id-generator-service.rest}"
)
public interface IdGeneratorServiceClient {

    @PostMapping("/identifiers")
    IdResponse generateId();

}