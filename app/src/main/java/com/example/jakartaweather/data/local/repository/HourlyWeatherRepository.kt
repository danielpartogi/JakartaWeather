package com.example.jakartaweather.data.local.repository

import androidx.lifecycle.LiveData
import com.example.jakartaweather.data.local.dao.WeatherHourlyDAO
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity
import com.example.jakartaweather.data.net.request.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HourlyWeatherRepository(private val dao: WeatherHourlyDAO) {


    val hourlyWeather: LiveData<List<WeatherDataHourlyEntity>> = dao.getItems()

    suspend fun getHourlyWeather() = Request.weather.getHourlyWeatherAsync()

    fun insertAll(items: List<WeatherDataHourlyEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertAll(items)
        }
    }
}