package com.example.jakartaweather.data.net.response

import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity

data class OpenWeatherHourlyResponse(

    val list: List<WeatherDataHourlyEntity>
)

data class OpenWeatherDailyResponse(
    val list: List<WeatherDataDailyEntity>
)