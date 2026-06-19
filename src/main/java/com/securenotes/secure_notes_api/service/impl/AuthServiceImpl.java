package com.securenotes.secure_notes_api.service.impl;

import com.securenotes.secure_notes_api.dto.request.LoginRequest;
import com.securenotes.secure_notes_api.dto.request.RegisterRequest;
import com.securenotes.secure_notes_api.dto.response.LoginResponse;
import com.securenotes.secure_notes_api.dto.response.UserResponse;
import com.securenotes.secure_notes_api.entity.Role;
import com.securenotes.secure_notes_api.entity.User;
import com.securenotes.secure_notes_api.repository.RoleRepository;
import com.securenotes.secure_notes_api.repository.UserRepository;
import com.securenotes.secure_notes_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(
            RegisterRequest request
    ) {

        if(userRepository.existsByEmail(
                request.email())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        Role role =
                roleRepository
                        .findByName("ROLE_USER")
                        .orElseThrow();

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(
                        passwordEncoder.encode(
                                request.password()
                        )
                )
                .enabled(true)
                .locked(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .roles(Set.of(role))
                .build();

        User saved =
                userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );
    }

    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        return new LoginResponse(
                "Login successful"
        );
    }
}
