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
}
