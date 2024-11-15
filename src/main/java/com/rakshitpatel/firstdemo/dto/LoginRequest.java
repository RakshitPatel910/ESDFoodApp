package com.rakshitpatel.firstdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotNull(message = "email needed")
        @NotEmpty(message = "email needed")
        @Email(message = "email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "password needed")
        @NotEmpty(message = "password needed")
        @NotBlank(message = "password needed")
        @Size(min = 8, max = 12)
        @JsonProperty("password")
        String password
) {
}
