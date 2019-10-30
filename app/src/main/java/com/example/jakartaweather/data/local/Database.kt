package com.example.jakartaweather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jakartaweather.data.local.dao.WeatherCurrentDAO
import com.example.jakartaweather.data.local.dao.WeatherDailyDAO
import com.example.jakartaweather.data.local.dao.WeatherHourlyDAO
import com.example.jakartaweather.data.local.entity.WeatherDataCurrentEntity
import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity

@Database(
    entities = [
        WeatherDataCurrentEntity::class,
        WeatherDataHourlyEntity::class,
        WeatherDataDailyEntity::class
    ], version = 12
)
@TypeConverters(TypeConverterWeather::class)
abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract fun weatherCurrentDao(): WeatherCurrentDAO
    abstract fun weatherHourlyDao(): WeatherHourlyDAO
    abstract fun weatherDailyDao(): WeatherDailyDAO

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(context: Context): WeatherRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
