package com.example.CS205.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface APIService2 {

}
//class APIService2 {
//
//}