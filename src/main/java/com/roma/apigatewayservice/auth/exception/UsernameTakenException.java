package com.roma.apigatewayservice.auth.exception;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(message);
    }
}
