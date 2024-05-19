package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.RecommendationDTO;
import com.hackfest.RecommendationSystem.service.RecommendationService;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.exceptions.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/createDatabase")
    public ResponseEntity<String> createDatabase() {

        return ResponseEntity.ok()
                .body(recommendationService.createDatabase());
    }

    @GetMapping("/resetDatabase")
    public ResponseEntity<String> resetDatabase() {

        return ResponseEntity.ok()
                .body(recommendationService.resetDatabase());
    }

    @GetMapping("/createMovieDatabase")
    public ResponseEntity<String> createMovieDatabase() {

        return ResponseEntity.ok()
                .body(recommendationService.createMovieDatabase());
    }

    @PostMapping("/populateDataOnRecombee")
    public ResponseEntity<String> populateDataOnRecombee() {
        return ResponseEntity.ok()
                .body(recommendationService.populateDataOnRecombee());
    }

    @GetMapping("/getAllData")
    public ResponseEntity<Item[]> getAllData() {
        return ResponseEntity.ok()
                .body(recommendationService.getAllData());
    }

    @GetMapping("/view")
    public ResponseEntity<String> viewMovie(@RequestParam String movieId) {
        return ResponseEntity.ok()
                .body(recommendationService.userViewedMovies(movieId));
    }

    @GetMapping("/get")
    public ResponseEntity<RecommendationDTO> recommendations(@RequestParam Integer pageNo, @RequestParam(required = false) Integer with_genres) throws ApiException {
        return ResponseEntity.ok()
                .body(recommendationService.recommendations(pageNo, with_genres));
    }

    @GetMapping("/history")
    public ResponseEntity<RecommendationDTO> history(@RequestParam Integer pageNo) {
        return ResponseEntity.ok()
                .body(recommendationService.watchHistory(pageNo));
    }
}
