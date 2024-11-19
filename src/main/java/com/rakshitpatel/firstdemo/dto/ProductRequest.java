package com.rakshitpatel.firstdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @JsonProperty("id")
        Long Id,

        @NotNull(message = "name needed")
        @NotEmpty(message = "name needed")
        @NotBlank(message = "name needed")
        @JsonProperty("name")
        String name,

        @NotNull(message = "price needed")
        @JsonProperty("price")
        Double price
) {
}
