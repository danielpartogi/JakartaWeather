package com.example.jakartaweather.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity

@Dao
interface WeatherDailyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<WeatherDataDailyEntity>)

    @Query("SELECT * FROM weather_data_daily")
    fun getItems() : LiveData<List<WeatherDataDailyEntity>>
}
