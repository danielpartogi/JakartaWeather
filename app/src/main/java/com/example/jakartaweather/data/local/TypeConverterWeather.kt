package com.example.jakartaweather.data.local

import androidx.room.TypeConverter
import com.example.jakartaweather.data.local.model.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class TypeConverterWeather {
    @TypeConverter
    fun jsonToListWeather(json: String?): ArrayList<WeatherModel>? {
        json?.let {
            val gson = Gson()
                val type = object : TypeToken<List<WeatherModel>>() {

            }.type
            return gson.fromJson(json, type)
        }
        return null
    }

    @TypeConverter
    fun listWeatherToJson(list: ArrayList<WeatherModel>?): String? {
        list?.let {
            val gson = Gson()
            val type = object : TypeToken<List<WeatherModel>>() {

            }.type
            return gson.toJson(list, type)
        }
        return null
    }
}