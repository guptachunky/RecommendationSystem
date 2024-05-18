package com.hackfest.RecommendationSystem.dto;

import com.hackfest.RecommendationSystem.entity.Movies;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class MovieDto {
    String title;
    Set<String> genres;
    Double popularity;
    Double voteAverage;
    Date release_date;
    String id;

    public MovieDto(Movies movies) {
        this.popularity = movies.getPopularity();
        this.title = movies.getTitle();
        this.id = movies.getId();
        this.voteAverage = movies.getVote_average();
        this.genres = movies.getGenres();
        this.release_date = movies.getRelease_date();
    }
}
