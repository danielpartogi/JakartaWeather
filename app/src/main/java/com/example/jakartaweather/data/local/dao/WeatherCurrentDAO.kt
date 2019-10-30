package com.example.jakartaweather.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jakartaweather.data.local.entity.WeatherDataCurrentEntity

@Dao
interface WeatherCurrentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WeatherDataCurrentEntity)

    @Query("SELECT * FROM WEATHER_DATA_CURRENT LIMIT -1")
    fun getItem() : LiveData<WeatherDataCurrentEntity>
}
