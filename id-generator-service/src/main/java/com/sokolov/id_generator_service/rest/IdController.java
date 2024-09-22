package com.sokolov.id_generator_service.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sokolov.id_generator_service.domain.IdGeneratorService;
import com.sokolov.id_generator_service.rest.model.IdResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/identifiers")
public class IdController {

    private final IdGeneratorService idGeneratorService;

    @PostMapping
    public IdResponse generateId() {
        String identifier = idGeneratorService.generate();
        return new IdResponse(identifier);
    }

}