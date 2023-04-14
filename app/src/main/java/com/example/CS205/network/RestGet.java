package com.example.CS205.network;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Multi-threaded get request with logic for JSON parsing to names and scores
 * */
public class RestGet extends Thread {
    private Map<String, Integer> scores;

    @Override
    public void run() {
        scores = new LinkedHashMap<>();
        String results;

        ApiInterface service =  ApiService.getClient().create(ApiInterface.class);

        try {
            results = service.getResources().execute().body();
            Log.d("RestRequest.java", results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (results != null && results.contains("Name")) {
            String[] lines = results.split("\\r?\\n");
            for (int i = 1; i < lines.length - 1; i++) {
                String line = lines[i].split("\\}")[0];
                int nameIdx = line.indexOf("Name") + 7;
                int scoreIdx = line.indexOf("Score");
                String name = line.substring(nameIdx, scoreIdx - 3);
                if (scores.containsKey(name)) continue;
                Integer score = Integer.parseInt(line.substring(scoreIdx + 7));
                scores.put(name, score);
            }
        }
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public String getScoresAsString() {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : scores.keySet()) {
            mapAsString.append(key + "=" + scores.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
