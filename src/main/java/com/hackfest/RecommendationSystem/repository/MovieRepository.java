package com.hackfest.RecommendationSystem.repository;

import com.hackfest.RecommendationSystem.entity.Movies;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movies, String> {
    List<Movies> findAllByIdIn(List<String> ids);
}
