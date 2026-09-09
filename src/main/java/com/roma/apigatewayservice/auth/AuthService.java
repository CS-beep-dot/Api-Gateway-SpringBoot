package com.roma.apigatewayservice.auth;

import com.roma.apigatewayservice.domain.entity.Role;
import com.roma.apigatewayservice.domain.entity.User;
import com.roma.apigatewayservice.domain.repository.RoleRepository;
import com.roma.apigatewayservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User userRegister(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("ROLE_USER not seeded in DataBase"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles(Set.of(userRole));

        return userRepository.save(user);
    }


}
