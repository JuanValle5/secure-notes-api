package com.securenotes.secure_notes_api.service;

import com.securenotes.secure_notes_api.dto.request.LoginRequest;
import com.securenotes.secure_notes_api.dto.request.RegisterRequest;
import com.securenotes.secure_notes_api.dto.response.LoginResponse;
import com.securenotes.secure_notes_api.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(
            RegisterRequest request
    );

    LoginResponse login(
            LoginRequest request
    );
}