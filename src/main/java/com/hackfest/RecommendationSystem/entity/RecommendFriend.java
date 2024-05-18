package com.hackfest.RecommendationSystem.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("recommend_friend")
@Getter
@Setter
@RequiredArgsConstructor
public class RecommendFriend {

    @Id
    String username;

    List<PersonalRecommendation> recommendations = new ArrayList<>();
}
