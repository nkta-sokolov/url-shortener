package com.sokolov.id_generator_service.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.f4b6a3.uuid.codec.base.Base62Codec;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IdGeneratorService {

    @Value("${id.length}")
    private final Integer length;

    public String generate() {
        UUID uuid = UUID.randomUUID();
        String encoded = Base62Codec.INSTANCE.encode(uuid);
        return encoded.substring(0, length);
    }

}