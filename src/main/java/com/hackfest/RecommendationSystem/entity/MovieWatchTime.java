package com.hackfest.RecommendationSystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class MovieWatchTime implements Comparable<MovieWatchTime> {
    String movieId;
    Date watchedAt;

    public MovieWatchTime(String movieId) {
        this.movieId = movieId;
        this.watchedAt = new Date();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId());
    }

    @Override
    public int compareTo(@NotNull MovieWatchTime movieWatchTime) {
        return getWatchedAt().compareTo(movieWatchTime.getWatchedAt());
    }
}
