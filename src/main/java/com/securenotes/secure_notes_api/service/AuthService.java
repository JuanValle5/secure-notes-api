package com.securenotes.secure_notes_api.service;

import com.securenotes.secure_notes_api.dto.request.RegisterRequest;
import com.securenotes.secure_notes_api.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(
            RegisterRequest request
    );
}