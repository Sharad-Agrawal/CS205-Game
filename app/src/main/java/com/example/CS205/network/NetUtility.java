package com.example.CS205.network;

import java.util.Map;

public class NetUtility {
    public static Map<String, Integer> getLeaderboard() {
        RestGet restRequest = new RestGet();
        restRequest.start();
        try {
            restRequest.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restRequest.getScores();
    }

    public static String getLeaderboardAsString() {
        RestGet restRequest = new RestGet();
        restRequest.start();
        try {
            restRequest.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restRequest.getScoresAsString();
    }

    public static boolean saveScoreToLeaderboard(String name, Integer score) {
        RestPost restPost = new RestPost(name, score);
        restPost.start();
        try {
            restPost.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  restPost.isSuccess();
    }
}
