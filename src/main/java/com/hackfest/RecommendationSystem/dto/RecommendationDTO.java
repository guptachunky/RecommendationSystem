package com.hackfest.RecommendationSystem.dto;

import com.hackfest.RecommendationSystem.entity.Movies;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecommendationDTO {
    Integer page;
    List<Movies> results;
    Integer total_pages;
    Integer total_results;

    public RecommendationDTO(Integer page, List<Movies> movies) {
        this.page = page;
        this.results = movies;
        this.total_pages = this.total_results = 1500;
    }
}
