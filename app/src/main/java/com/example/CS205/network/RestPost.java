package com.example.CS205.network;

import android.util.Log;

/**
 * Multi-threaded post request to save name and score to online database
 * */
public class RestPost extends Thread {
    private final String name;
    private final String score;
    private boolean success;


    public RestPost(String name, int score) {
        this.name = "{\"Name\": \"" + name + "\",";
        this.score = "\"Score\": " + score + "}";
    }


    @Override
    public void run() {
        String body = name + score;
        String results;


        ApiInterface service = ApiService.getClient().create(ApiInterface.class);

        try {
            success = service.saveToDatabase(body).execute().isSuccessful();
            results = success ? "Success" : "Failure";
            Log.d("RestPost.java", results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSuccess() {return success;}
}
