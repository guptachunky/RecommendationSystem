package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.FriendDto;
import com.hackfest.RecommendationSystem.dto.RecommendDto;
import com.hackfest.RecommendationSystem.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/friend")

public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(FriendDto friendDto) {
        return ResponseEntity.ok()
                .body(friendService.addFriend(friendDto));
    }

    @PostMapping("/recommend")
    public ResponseEntity<String> addFriend(@RequestBody RecommendDto recommendDto) {
        return ResponseEntity.ok()
                .body(friendService.recommendFriend(recommendDto));
    }

}
