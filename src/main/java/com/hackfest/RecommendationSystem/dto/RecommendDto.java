package com.hackfest.RecommendationSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class RecommendDto {
    String movieId;
    List<String> friends;
}
