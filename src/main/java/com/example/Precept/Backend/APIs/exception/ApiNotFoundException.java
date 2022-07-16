package com.example.Precept.Backend.APIs.exception;

public class ApiNotFoundException extends RuntimeException {

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }
}
