package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.dto.UpdatedMovieDto;
import com.hackfest.RecommendationSystem.entity.Movies;
import com.hackfest.RecommendationSystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    MovieRepository movieRepository;

    public String upload(List<UpdatedMovieDto> updatedMovieDto) {
        List<UpdatedMovieDto> filtersMovie = updatedMovieDto.stream().filter(it -> List.of("en", "hi")
                .contains(it.getOriginal_language()) && it.getGenres() != null && !it.getGenres().isEmpty()).toList();
        List<Movies> allMovies = filtersMovie.stream().map(Movies::new).toList();
        movieRepository.saveAll(allMovies);
        return "Uploaded";
    }

}
