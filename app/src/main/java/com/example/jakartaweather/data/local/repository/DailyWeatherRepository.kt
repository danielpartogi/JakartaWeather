package com.example.jakartaweather.data.local.repository

import androidx.lifecycle.LiveData
import com.example.jakartaweather.data.local.dao.WeatherDailyDAO
import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity
import com.example.jakartaweather.data.net.request.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyWeatherRepository(private val dao: WeatherDailyDAO) {


    val dailyWeather: LiveData<List<WeatherDataDailyEntity>> = dao.getItems()

    suspend fun getDailyWeather() = Request.weather.getDailyWeatherAsync()

    fun insertAll(items: List<WeatherDataDailyEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertAll(items)
        }
    }
}