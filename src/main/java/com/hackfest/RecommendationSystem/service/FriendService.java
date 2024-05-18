package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.dto.FriendDto;
import com.hackfest.RecommendationSystem.dto.RecommendDto;
import com.hackfest.RecommendationSystem.entity.Friends;
import com.hackfest.RecommendationSystem.entity.PersonalRecommendation;
import com.hackfest.RecommendationSystem.entity.RecommendFriend;
import com.hackfest.RecommendationSystem.repository.FriendRepository;
import com.hackfest.RecommendationSystem.repository.RecommendationRepository;
import com.hackfest.RecommendationSystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    ThreadLocalUtil threadLocalUtil;

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    RecommendationRepository recommendationRepository;

    public String addFriend(FriendDto friendDtos) {
        String username = threadLocalUtil.getRequestTokenDetails();
        Optional<Friends> friends = friendRepository.findById(username);
        if (friends.isEmpty()) {
            friends = Optional.of(new Friends());
            friends.get().setUsername(username);
        }
        friends.get().getFriend().addAll(friendDtos.getUsername());
        friendRepository.save(friends.get());
        return "Saved";
    }

    public String recommendFriend(RecommendDto recommendDto) {
        String username = threadLocalUtil.getRequestTokenDetails();
        recommendDto.getFriends().forEach(it -> {
            Optional<RecommendFriend> recommendFriend = recommendationRepository.findById(it);
            if (recommendFriend.isEmpty()) {
                recommendFriend = Optional.of(new RecommendFriend());
                recommendFriend.get().setUsername(it);
            }
            PersonalRecommendation personalRecommendation = new PersonalRecommendation();
            personalRecommendation.setRecommendedBy(username);
            personalRecommendation.setMovieId(recommendDto.getMovieId());
            recommendFriend.get().getRecommendations().add(personalRecommendation);
            recommendationRepository.save(recommendFriend.get());
        });
        return "Recommended";
    }
}
