package com.example.Precept.Backend.APIs.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(Throwable cause) {
        super(cause);
    }
}
