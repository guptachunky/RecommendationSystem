package com.hackfest.RecommendationSystem.utils;

import java.util.jar.Attributes;

public class UserThreadLocal {
    public static final InheritableThreadLocal<String> userThreadLocal = new InheritableThreadLocal();
    public static final InheritableThreadLocal<Attributes> customAttributesThreadLocal = new InheritableThreadLocal();

    public UserThreadLocal() {
    }

    public static void setTokenDetails(String username) {
        userThreadLocal.set(username);
    }

    public static String getTokenDetails() {
        return userThreadLocal.get();
    }


}
