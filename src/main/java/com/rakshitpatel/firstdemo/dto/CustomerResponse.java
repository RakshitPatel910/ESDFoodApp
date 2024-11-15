package com.rakshitpatel.firstdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("first_name")
        String fistName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("email")
        String email
) {
}
