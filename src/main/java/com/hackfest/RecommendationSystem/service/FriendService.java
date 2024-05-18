package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.entity.User;
import com.hackfest.RecommendationSystem.repository.FriendRepository;
import com.hackfest.RecommendationSystem.dto.FriendDto;
import com.hackfest.RecommendationSystem.dto.RecommendDto;
import com.hackfest.RecommendationSystem.entity.Friends;
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

    public String addFriend(FriendDto friendDtos) {
        String username = threadLocalUtil.getRequestTokenDetails();
        Optional<Friends> friends = friendRepository.findById(username);
        if (friends.isEmpty()) {
            friends = Optional.of(new Friends());
            friends.get().setUsername(username);
        }
        friends.get().getFriend().add(friendDtos.getUsername());
        friendRepository.save(friends.get());
        return "Saved";
    }

    public String recommendFriend(RecommendDto recommendDto) {
        return "Recommended";
    }
}
