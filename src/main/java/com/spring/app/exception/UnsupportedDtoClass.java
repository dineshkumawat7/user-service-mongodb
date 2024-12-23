package com.spring.app.exception;

public class UnsupportedDtoClass extends RuntimeException {
    public UnsupportedDtoClass(String message) {
        super(message);
    }
}
