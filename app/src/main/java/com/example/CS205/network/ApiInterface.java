package com.example.CS205.network;

import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App",
            "x-apikey: 6746ec2b9d2dbfc6d6eb79396f6c84e0ce55f",
            "cache-control: no-cache"
    })
    @GET("")
    public String getResources();
}
