package com.gorohovart.task2;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class AnalyticsStorage {

    private HashMap<String, UserStats> userStatsMap = new HashMap<>();

    public HashMap<String, UserStats> getUserStatsMap() {
        return userStatsMap;
    }
}
