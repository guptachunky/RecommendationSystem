package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.FriendDto;
import com.hackfest.RecommendationSystem.dto.RecommendDto;
import com.hackfest.RecommendationSystem.entity.Movies;
import com.hackfest.RecommendationSystem.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/friend")

public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestBody FriendDto friendDto) {
        Boolean success = friendService.addFriend(friendDto);
        if (Boolean.TRUE.equals(success)) {
            return ResponseEntity.ok()
                    .body("Saved Success");
        } else {
            return ResponseEntity.badRequest()
                    .body("Failure");
        }

    }

    @PostMapping("/recommend")
    public ResponseEntity<String> addFriend(@RequestBody RecommendDto recommendDto) {
        return ResponseEntity.ok()
                .body(friendService.recommendFriend(recommendDto));
    }

    @GetMapping("/myFriends")
    public ResponseEntity<Map<String, String>> getFriend() {
        return ResponseEntity.ok()
                .body(friendService.getMyFriends());
    }
    @GetMapping("/recommendationToMe")
    public ResponseEntity<List<Movies>> fromFriends() {
        return ResponseEntity.ok()
                .body(friendService.fromFriends());
    }

}
