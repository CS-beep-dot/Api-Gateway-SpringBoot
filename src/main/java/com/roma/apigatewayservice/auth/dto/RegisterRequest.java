package com.roma.apigatewayservice.auth.dto;

public record RegisterRequest (
    String username,
    String password
) {}
