package com.roma.apigatewayservice.auth;


import com.roma.apigatewayservice.auth.dto.AuthResponse;
import com.roma.apigatewayservice.auth.dto.LoginRequest;
import com.roma.apigatewayservice.auth.dto.RegisterRequest;
import com.roma.apigatewayservice.domain.entity.User;
import com.roma.apigatewayservice.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = authService.userRegister(request.username(), request.password());
        String token = jwtService.generateToken(user.getUsername(), rolesOf(user));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.username(), request.password());
        String token = jwtService.generateToken(user.getUsername(), rolesOf(user));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    private Set<String> rolesOf(User user) {
        return user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
