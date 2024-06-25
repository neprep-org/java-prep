package com.rca.myspringsecurity.error;


public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
