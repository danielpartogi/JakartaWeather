package com.example.jakartaweather.data.net.request

import com.example.jakartaweather.data.local.entity.WeatherDataCurrentEntity
import com.example.jakartaweather.data.net.ApiConfig
import com.example.jakartaweather.data.net.response.OpenWeatherDailyResponse
import com.example.jakartaweather.data.net.response.OpenWeatherHourlyResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRequest {
    @GET("${ApiConfig.URL_PATH}/weather?")
    suspend fun getCurrentWeatherAsync(
        @Query("id") id: String = ApiConfig.CODE_ZIP,
        @Query("appid") appid: String = ApiConfig.TOKEN,
        @Query("units")units: String = "metric"
    ): WeatherDataCurrentEntity

    @GET("${ApiConfig.URL_PATH}/forecast?")
    suspend fun getHourlyWeatherAsync(
        @Query("id") id: String = ApiConfig.CODE_ZIP,
        @Query("appid") appid: String = ApiConfig.TOKEN,
        @Query("units") units: String = "metric"
    ) : OpenWeatherHourlyResponse

    @GET("${ApiConfig.URL_PATH}/forecast?")
    suspend fun getDailyWeatherAsync(
        @Query("id") id: String = ApiConfig.CODE_ZIP,
        @Query("appid") appid: String = ApiConfig.TOKEN,
        @Query("units") units: String = "metric"
    ) : OpenWeatherDailyResponse
}

