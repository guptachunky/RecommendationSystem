package com.hackfest.RecommendationSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDTO {

    String username;
    String password;
    String name;
}
