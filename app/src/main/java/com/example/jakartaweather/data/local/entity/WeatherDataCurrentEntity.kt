package com.example.jakartaweather.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jakartaweather.data.local.model.TemperatureModel
import com.example.jakartaweather.data.local.model.WeatherModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_data_current")
data class WeatherDataCurrentEntity(
    @PrimaryKey
    val id: Int,
    val weather: ArrayList<WeatherModel>?,
    @Embedded(prefix = "current_")
    val main: TemperatureModel?,
    val name: String?,
    @SerializedName("dt")
    val dateTime: Long?
)