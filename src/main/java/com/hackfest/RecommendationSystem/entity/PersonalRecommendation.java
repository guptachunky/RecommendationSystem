package com.hackfest.RecommendationSystem.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PersonalRecommendation {
    String movieId;
    String recommendedBy;
}
