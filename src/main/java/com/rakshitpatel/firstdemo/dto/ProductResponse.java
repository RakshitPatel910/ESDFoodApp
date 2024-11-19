package com.rakshitpatel.firstdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("Id")
        Long Id,

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        Double price
) {
}
