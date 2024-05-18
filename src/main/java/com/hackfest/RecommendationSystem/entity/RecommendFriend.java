package com.hackfest.RecommendationSystem.entity;

import com.recombee.api_client.bindings.Recommendation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document("recommend_friend")
@Getter
@Setter
@RequiredArgsConstructor
public class RecommendFriend {

    @Id
    String username;

    List<PersonalRecommendation> recommendations;
}
