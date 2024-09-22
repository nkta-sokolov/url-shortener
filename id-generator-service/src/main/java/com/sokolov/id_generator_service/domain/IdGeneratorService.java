package com.sokolov.id_generator_service.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IdGeneratorService {

    private final SequenceGenerator sequenceGenerator;

    private final Base62Converter base62Converter;

    public String generate() {
        long sequence = sequenceGenerator.generate();
        return base62Converter.convert(sequence);
    }

}