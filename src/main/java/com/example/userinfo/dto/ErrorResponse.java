package com.example.userinfo.dto;

public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String path
) {}
