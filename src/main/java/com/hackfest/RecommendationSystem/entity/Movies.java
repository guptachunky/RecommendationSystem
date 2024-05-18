package com.hackfest.RecommendationSystem.entity;

import com.hackfest.RecommendationSystem.dto.UpdatedMovieDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Document("movies")
public class Movies {
    @Id
    String id;
    Set<String> genres;
    Boolean adult;
    String backdrop_path;
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

    public Movies(UpdatedMovieDto updatedMovieDto) {
        this.id = updatedMovieDto.getId();
        this.genres = updatedMovieDto.getGenres();
        this.adult = updatedMovieDto.getAdult();
        this.backdrop_path = updatedMovieDto.getBackdrop_path();
        this.original_language = updatedMovieDto.getOriginal_language();
        this.original_title = updatedMovieDto.getOriginal_title();
        this.title = updatedMovieDto.getTitle();
        this.overview = updatedMovieDto.getOverview();
        this.popularity = updatedMovieDto.getPopularity();
        this.poster_path = updatedMovieDto.getPoster_path();
        this.release_date = updatedMovieDto.getRelease_date();
        this.video = updatedMovieDto.getVideo();
        this.vote_average = updatedMovieDto.getVote_average();
        this.vote_count = updatedMovieDto.getVote_count();
    }
}
