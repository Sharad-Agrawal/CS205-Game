package com.example.CS205.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Interface for defining get and post requests*/
public interface ApiInterface {
    @Headers({
            "User-Agent: Retrofit-Sample-App",
            "x-apikey: 6746ec2b9d2dbfc6d6eb79396f6c84e0ce55f",
            "cache-control: no-cache"
    })
    @GET("high-score?q={}&h={\"$orderby\": {\"Score\": -1}}")
    Call<String> getResources();

    @Headers({
            "content-type: application/json",
            "User-Agent: Retrofit-Sample-App",
            "x-apikey: 6746ec2b9d2dbfc6d6eb79396f6c84e0ce55f",
    })
    @POST("high-score")
    Call<String> saveToDatabase(@Body String body);
}
