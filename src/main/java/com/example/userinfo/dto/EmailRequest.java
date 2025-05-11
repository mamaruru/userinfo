package com.example.userinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record EmailRequest(
        @Schema(description = "ユーザーのメールアドレス一覧", example = "[\"alice@example.com\",\"bob@example.com\"]")
        List<String> emails
) {}