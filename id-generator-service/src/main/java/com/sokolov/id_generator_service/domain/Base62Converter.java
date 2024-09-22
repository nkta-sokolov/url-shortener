package com.sokolov.id_generator_service.domain;

import org.springframework.stereotype.Service;

@Service
public class Base62Converter {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int BASE = CHARACTERS.length();

    private static final int REQUIRED_LENGTH = 6;

    public String convert(long number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (number > 0) {
            int index = (int) (number % BASE);
            stringBuilder.append(CHARACTERS.charAt(index));
            number /= BASE;
        }

        while (stringBuilder.length() < REQUIRED_LENGTH) {
            stringBuilder.insert(0, CHARACTERS.charAt(0));
        }

        return stringBuilder.toString();
    }

}