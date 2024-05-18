package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.UpdatedMovieDto;
import com.hackfest.RecommendationSystem.service.DataService;
import com.hackfest.RecommendationSystem.service.RecommendationService;
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
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService dataService;
    @Autowired
    RecommendationService recommendationService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody List<UpdatedMovieDto> updatedMovieDto) {
        return ResponseEntity.ok()
                .body(dataService.upload(updatedMovieDto));
    }

    @PostMapping("/addUsers")
    public ResponseEntity<String> addUsers() {
        return ResponseEntity.ok()
                .body(recommendationService.addUsers());
    }
}
