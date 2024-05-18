package com.hackfest.RecommendationSystem.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("watch_history")
@Getter
@Setter
@RequiredArgsConstructor
public class WatchHistory {
    @Id
    String username;

    Set<MovieWatchTime> movieWatched = new HashSet<>();
}
