package com.roma.apigatewayservice.auth.dto;

public record LoginRequest (
        String username,
        String password
) {}
