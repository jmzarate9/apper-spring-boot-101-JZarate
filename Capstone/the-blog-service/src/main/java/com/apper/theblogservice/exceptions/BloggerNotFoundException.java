package com.apper.theblogservice.exceptions;

public class BloggerNotFoundException extends RuntimeException {
    public BloggerNotFoundException(String message) {
        super(message);
    }
}
