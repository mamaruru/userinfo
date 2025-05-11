package com.example.userinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UserInfoResponse(
        @Schema(example = "alice@example.com") String email,
        @Schema(example = "Alice") String name,
        @Schema(example = "1990-01-01") LocalDate birthDate,
        @Schema(example = "FEMALE") String gender
) {}
