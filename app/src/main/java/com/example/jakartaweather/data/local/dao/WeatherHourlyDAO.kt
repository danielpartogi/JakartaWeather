package com.example.jakartaweather.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity

@Dao
interface WeatherHourlyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<WeatherDataHourlyEntity>)

    @Query("SELECT * FROM weather_data_hourly")
    fun getItems() : LiveData<List<WeatherDataHourlyEntity>>
}
