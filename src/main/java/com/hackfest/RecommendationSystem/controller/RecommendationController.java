package com.hackfest.RecommendationSystem.controller;

import com.hackfest.RecommendationSystem.dto.MovieDto;
import com.hackfest.RecommendationSystem.service.RecommendationService;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.exceptions.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/createDummyData")
    public ResponseEntity<String> createDummyData(@RequestBody List<MovieDto> movieDtoList) {
        return ResponseEntity.ok()
                .body(recommendationService.createDummyData(movieDtoList));
    }

    @GetMapping("/getAllData")
    public ResponseEntity<Item[]> getAllData() {
        return ResponseEntity.ok()
                .body(recommendationService.getAllData());
    }

    @GetMapping("/viewMovie")
    public ResponseEntity<String> viewMovie() {
        return ResponseEntity.ok()
                .body(recommendationService.userViewedMovies());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<RecommendationResponse> recommendations() throws ApiException {
        return ResponseEntity.ok()
                .body(recommendationService.recommendations());
    }


}
