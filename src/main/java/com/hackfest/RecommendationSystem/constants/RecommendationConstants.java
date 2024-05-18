package com.hackfest.RecommendationSystem.constants;


import com.hackfest.RecommendationSystem.entity.User;

import java.util.HashMap;
import java.util.Map;

public interface RecommendationConstants {
    Map<String, User> USER_MAP = new HashMap<>() {{
        put("chunkygupta", new User("chunkygupta", "Chunky Gupta", "password"));
        put("meghagupta", new User("meghagupta", "Megha Gupta", "password"));
    }};
}
