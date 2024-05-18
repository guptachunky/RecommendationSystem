package com.hackfest.RecommendationSystem.utils;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUtil {

    public void setRequestTokenDetails(String username) {
        UserThreadLocal.setTokenDetails(username);
    }

    public String getRequestTokenDetails() {
        return UserThreadLocal.getTokenDetails();
    }
}
