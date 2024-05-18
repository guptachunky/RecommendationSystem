package com.hackfest.RecommendationSystem.repository;

import com.hackfest.RecommendationSystem.entity.Movies;
import com.hackfest.RecommendationSystem.entity.RecommendFriend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends MongoRepository<RecommendFriend, String> {

}
