package com.sokolov.id_generator_service.domain.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Converter {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int BASE = CHARACTERS.length();

    public String convert(long number) {
        StringBuilder stringBuilder = new StringBuilder();
        while (number > 0) {
            stringBuilder.append(CHARACTERS.charAt((int) (number % BASE)));
            number /= BASE;
        }
        return stringBuilder.reverse().toString();
    }

}