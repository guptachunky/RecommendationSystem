package com.hackfest.RecommendationSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class MovieDto {
    String title;
    Integer year;
    Set<String> genres;
    String duration;
    String storyline;
    Set<String> actors;
    String posterurl;
    Double imdbRating;
}
