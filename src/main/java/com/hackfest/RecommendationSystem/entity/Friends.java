package com.hackfest.RecommendationSystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("friends")
@Getter
@Setter
public class Friends {

    @Id
    String username;

    Set<String> friend = new HashSet<>();

}
