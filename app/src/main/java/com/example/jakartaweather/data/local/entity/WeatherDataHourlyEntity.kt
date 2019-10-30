package com.example.jakartaweather.data.local.entity

import androidx.room.*
import com.example.jakartaweather.data.local.TypeConverterWeather
import com.example.jakartaweather.data.local.model.TemperatureModel
import com.example.jakartaweather.data.local.model.WeatherModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_data_hourly")
data class WeatherDataHourlyEntity(

    @PrimaryKey
    var id: String,
    @SerializedName("dt")
    val dateTime: Long,
    @Embedded(prefix = "hourly_")
    val main : TemperatureModel,
    val weather: ArrayList<WeatherModel>?

)