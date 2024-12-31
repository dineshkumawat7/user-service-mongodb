package com.spring.app.exception;

public class UnsupportedDtoClassException extends RuntimeException {
    public UnsupportedDtoClassException(String message) {
        super(message);
    }
}
