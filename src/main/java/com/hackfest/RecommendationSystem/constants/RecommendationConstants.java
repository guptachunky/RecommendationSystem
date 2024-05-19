package com.hackfest.RecommendationSystem.constants;


import com.hackfest.RecommendationSystem.entity.User;

import java.util.HashMap;
import java.util.Map;

public interface RecommendationConstants {
    Map<String, User> USER_MAP = new HashMap<>() {{
        put("chunky.gupta", new User("chunky.gupta", "Chunky Gupta", "password"));
        put("daman.arora", new User("daman.arora", "Daman Arora", "password"));
        put("vishal.tripathi", new User("vishal.tripathi", "Vishal Tripathi", "password"));
        put("komal.garg", new User("komal.garg", "Komal Garg", "password"));
        put("gurkirat.singh", new User("gurkirat.singh", "Gurkirat Singh", "password"));
    }};
    Map<Integer, String> GENRE_MAP = new HashMap<>() {{
        put(28, "Action");
        put(12, "Adventure");
        put(16, "Animation");
        put(35, "Comedy");
        put(80, "Crime");
        put(99, "Documentary");
        put(18, "Drama");
        put(10751, "Family");
        put(14, "Fantasy");
        put(36, "History");
        put(27, "Horror");
        put(10402, "Music");
        put(9648, "Mystery");
        put(10749, "Romance");
        put(878, "Science Fiction");
        put(10770, "TV Movie");
        put(53, "Thriller");
        put(10752, "War");
        put(37, "Western");
    }};
}
