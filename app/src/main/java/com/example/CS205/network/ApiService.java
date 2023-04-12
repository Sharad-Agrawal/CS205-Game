package com.example.CS205.network;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {
    private static final String BASE_URL =
            "https://cs205-2073.restdb.io/rest/high-score";
    private static Retrofit retrofit;

//    public ApiService() {
//        retrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
//    }

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

}
