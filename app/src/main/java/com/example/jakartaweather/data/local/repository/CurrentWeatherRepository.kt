package com.example.jakartaweather.data.local.repository

import com.example.jakartaweather.data.local.dao.WeatherCurrentDAO
import com.example.jakartaweather.data.local.entity.WeatherDataCurrentEntity
import com.example.jakartaweather.data.net.request.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherRepository(private val dao: WeatherCurrentDAO) {


    val currentWeather = dao.getItem()

    suspend fun getCurrentWeather() = Request.weather.getCurrentWeatherAsync()

    fun insert(item: WeatherDataCurrentEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(item)
        }
    }
}