package com.hackfest.RecommendationSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdatedMovieDto {
    Set<String> genres;
    Boolean adult;
    String backdrop_path;
    String id;
    String original_language;
    String original_title;
    String title;
    String overview;
    Double popularity;
    String poster_path;
    Date release_date;
    Boolean video;
    Double vote_average;
    Integer vote_count;


}
