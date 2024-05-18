package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.dto.FriendDto;
import com.hackfest.RecommendationSystem.dto.RecommendDto;
import com.hackfest.RecommendationSystem.entity.Friends;
import com.hackfest.RecommendationSystem.entity.PersonalRecommendation;
import com.hackfest.RecommendationSystem.entity.RecommendFriend;
import com.hackfest.RecommendationSystem.entity.User;
import com.hackfest.RecommendationSystem.repository.FriendRepository;
import com.hackfest.RecommendationSystem.repository.RecommendationRepository;
import com.hackfest.RecommendationSystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.hackfest.RecommendationSystem.constants.RecommendationConstants.USER_MAP;

@Service
public class FriendService {

    @Autowired
    ThreadLocalUtil threadLocalUtil;

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    RecommendationRepository recommendationRepository;

    public Boolean addFriend(FriendDto friendDtos) {
        String username = threadLocalUtil.getRequestTokenDetails();
        Optional<Friends> friends = friendRepository.findById(username);
        if (friends.isEmpty()) {
            friends = Optional.of(new Friends());
            friends.get().setUsername(username);
        }
        boolean isSuccess = false;
        if (friendDtos.getUsername() != null && !friendDtos.getUsername().isEmpty()) {
            for (String friendUsername : friendDtos.getUsername()) {
                if (USER_MAP.containsKey(friendUsername)) {
                    friends.get().getFriend().add(friendUsername);
                    isSuccess = true;
                }

            }
        }
        friendRepository.save(friends.get());
        return isSuccess;
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

    public Map<String, String> getMyFriends() {
        String username = threadLocalUtil.getRequestTokenDetails();
        Optional<Friends> friends = friendRepository.findById(username);
        if (friends.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, String> map = new HashMap<>();
        friends.get().getFriend().forEach(it -> {
            User user = USER_MAP.get(it);
            map.put(user.getUsername(), user.getName());
        });
        return map;
    }
}
