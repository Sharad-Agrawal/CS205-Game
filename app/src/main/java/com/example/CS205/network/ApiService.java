package com.example.CS205.network;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Utility class to make downloading and uploading scores easier
 * */
public class ApiService {
    private static final String BASE_URL =
            "https://cs205-2073.restdb.io/rest/";

    /**
     * Retrofit library used to sent HTTP requests
     * */
    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

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

    /**
     * Returns true if score has been successfully uploaded to database
     * */
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
