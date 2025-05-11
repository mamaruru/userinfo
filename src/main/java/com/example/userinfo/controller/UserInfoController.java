package com.example.userinfo.controller;

import com.example.userinfo.dto.EmailRequest;
import com.example.userinfo.dto.ErrorResponse;
import com.example.userinfo.dto.UserInfoResponse;
import com.example.userinfo.service.ExternalUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/users")
@Tag(name = "User Info API", description = "社内向けユーザー情報取得API")
public class UserInfoController {

    private final ExternalUserService externalUserService;

    public UserInfoController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    @Operation(
            summary = "メールアドレスからユーザー情報を取得",
            description = "指定されたメールアドレス一覧から、名前・生年月日・性別を外部API経由で取得します",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ユーザー情報取得成功"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "内部サーバーエラー",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"timestamp\":\"2025-05-11T00:19:26.514+00:00\",\"status\":500,\"error\":\"Internal Server Error\",\"path\":\"/internal/users/lookup\"}"
                                    )
                            )
                    )
            }
    )
    @PostMapping("/lookup")
    public ResponseEntity<List<UserInfoResponse>> lookupUsers(
            @RequestBody EmailRequest request) {
        List<UserInfoResponse> result = externalUserService.fetchUserInfo(request.emails());
        return ResponseEntity.ok(result);
    }
}
