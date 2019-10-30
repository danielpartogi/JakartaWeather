package com.example.jakartaweather.data.net.request

import com.example.jakartaweather.data.net.ApiConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Request {

    val weather: WeatherRequest by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WeatherRequest::class.java)
    }
}