package com.example.jakartaweather.data.local.model

import com.google.gson.annotations.SerializedName


data class WeatherModel(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class TemperatureModel(
    @SerializedName("temp")
    val temperature: Float?,
    @SerializedName("temp_min")
    val minTemperature: Float?,
    @SerializedName("temp_max")
    val maxTemperature: Float?
)