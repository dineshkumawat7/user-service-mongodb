package com.spring.app.exception;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(){
        super("phone number already exists");
    }

    public PhoneAlreadyExistsException(String message){
        super(message);
    }
}
