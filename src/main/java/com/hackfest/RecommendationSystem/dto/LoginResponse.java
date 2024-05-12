package com.hackfest.RecommendationSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;

    private Long expiresIn;

    public LoginResponse(String jwtToken, long expirationTime) {
        this.token = jwtToken;
        this.expiresIn = expirationTime;
    }
}