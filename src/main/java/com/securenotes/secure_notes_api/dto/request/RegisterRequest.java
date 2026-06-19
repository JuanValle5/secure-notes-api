package com.securenotes.secure_notes_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        @Size(min = 3, max = 50)
        String username,

        @Email
        String email,

        @Size(min = 8)
        String password

) {
}