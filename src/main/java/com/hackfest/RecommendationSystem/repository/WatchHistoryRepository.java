package com.hackfest.RecommendationSystem.repository;

import com.hackfest.RecommendationSystem.entity.WatchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchHistoryRepository extends MongoRepository<WatchHistory, String> {
}
