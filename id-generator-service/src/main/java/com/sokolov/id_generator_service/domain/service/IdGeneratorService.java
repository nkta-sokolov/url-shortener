package com.sokolov.id_generator_service.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IdGeneratorService {

    @Value("${id.length}")
    private final Integer length;

    private final SequenceGenerator sequenceGenerator;

    private final Base62Converter base62Converter;

    public String generate() {
        long sequence = sequenceGenerator.nextId();
        return base62Converter.convert(sequence).substring(0, length);
    }

}