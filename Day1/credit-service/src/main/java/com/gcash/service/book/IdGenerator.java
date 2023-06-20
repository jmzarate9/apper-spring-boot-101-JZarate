package com.gcash.service.book;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerator {
    public String nextId() {
        return "BOOK_ID-" + UUID.randomUUID().toString();
    }
}
