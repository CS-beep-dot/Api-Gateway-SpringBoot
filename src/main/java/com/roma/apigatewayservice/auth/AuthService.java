package com.roma.apigatewayservice.auth;

import com.roma.apigatewayservice.auth.exception.UsernameTakenException;
import com.roma.apigatewayservice.domain.entity.Role;
import com.roma.apigatewayservice.domain.entity.User;
import com.roma.apigatewayservice.domain.repository.RoleRepository;
import com.roma.apigatewayservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
            throw new UsernameTakenException("Username is already taken!");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER not seeded in DataBase"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles(Set.of(userRole));

        return userRepository.save(user);
    }

    public User login(String userName, String rawPassword) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return user;
    }


}
