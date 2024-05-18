package com.hackfest.RecommendationSystem.repository;

import com.hackfest.RecommendationSystem.entity.Friends;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends MongoRepository<Friends, String> {

}
