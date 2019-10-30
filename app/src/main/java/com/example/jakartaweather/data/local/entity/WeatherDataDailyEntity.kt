package com.example.jakartaweather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jakartaweather.data.local.model.TemperatureModel
import com.example.jakartaweather.data.local.model.WeatherModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_data_daily")
data class WeatherDataDailyEntity(

    @PrimaryKey
    var id: String,
    @SerializedName("dt")
    val dateTime: Long,
    @Embedded(prefix = "hourly_")
    val main : TemperatureModel,
    val weather: ArrayList<WeatherModel>?

)