package com.securenotes.secure_notes_api.controller;

import com.securenotes.secure_notes_api.dto.request.LoginRequest;
import com.securenotes.secure_notes_api.dto.request.RegisterRequest;
import com.securenotes.secure_notes_api.dto.response.LoginResponse;
import com.securenotes.secure_notes_api.dto.response.UserResponse;
import com.securenotes.secure_notes_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(
            @RequestBody
            @Valid
            RegisterRequest request
    ) {

        return authService.register(
                request
        );
    }

    @PostMapping("/login")
    public LoginResponse login(

            @RequestBody
            @Valid
            LoginRequest request

    ) {

        return authService.login(
                request
        );
    }


}