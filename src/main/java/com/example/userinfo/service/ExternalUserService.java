package com.example.userinfo.service;

import com.example.userinfo.dto.EmailRequest;
import com.example.userinfo.dto.UserInfoResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ExternalUserService {

    private final WebClient externalApiClient;

    public ExternalUserService(@Qualifier("externalApiClient") WebClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    public List<UserInfoResponse> fetchUserInfo(List<String> emails) {
        return externalApiClient.post()
                .uri("/api/users/details")
                .bodyValue(new EmailRequest(emails))
                .retrieve()
                .onStatus(HttpStatusCode::isError, res ->
                        res.bodyToMono(String.class)
                                .flatMap(msg -> Mono.error(new RuntimeException("API error: " + msg))))
                .bodyToFlux(UserInfoResponse.class)
                .collectList()
                .block();
    }
}