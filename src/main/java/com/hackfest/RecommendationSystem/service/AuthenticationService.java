package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.dto.UserDTO;
import com.hackfest.RecommendationSystem.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.hackfest.RecommendationSystem.constants.RecommendationConstants.USER_MAP;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public User authenticate(UserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return USER_MAP.get(input.getUsername());
    }
}