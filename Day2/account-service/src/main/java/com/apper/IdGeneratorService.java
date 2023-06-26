package com.apper;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorService {
    public String generateRandomCharacters(int length) {
        String randomString = UUID.randomUUID().toString().replaceAll("-", "");
        return randomString.substring(0, length);
    }

    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
