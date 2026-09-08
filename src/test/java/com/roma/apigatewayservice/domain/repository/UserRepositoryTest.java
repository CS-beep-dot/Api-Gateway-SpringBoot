package com.roma.apigatewayservice.domain.repository;

import com.roma.apigatewayservice.domain.entity.Role;
import com.roma.apigatewayservice.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //running only JPA/Hibernate (Repositories/entities)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    //@Testcontainers and Container are for running Postgres on docker container
    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByUsername_returnsUser_whenUserExists() {
        //creating object for checking method
        Role userRole = roleRepository.save(new Role("ROLE_TEST_USER"));
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRoles(Set.of(userRole));
        userRepository.save(user);

        //testing if it finds user
        Optional<User> found = userRepository.findByUsername("testuser");

        //assert
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
        assertThat(found.get().getRoles()).hasSize(1);

    }

    @Test
    void existsByUsername_returnsFalse_whenUserDoesNotExist() {
        assertThat(userRepository.existsByUsername("nonexistent")).isFalse();
    }

}
