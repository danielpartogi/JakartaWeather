package com.example.jakartaweather.ui.main

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jakartaweather.data.local.WeatherRoomDatabase
import com.example.jakartaweather.data.local.dao.WeatherCurrentDAO
import com.example.jakartaweather.data.local.dao.WeatherDailyDAO
import com.example.jakartaweather.data.local.dao.WeatherHourlyDAO
import com.example.jakartaweather.data.local.entity.WeatherDataCurrentEntity
import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity
import com.example.jakartaweather.data.local.model.WeatherModel
import com.example.jakartaweather.data.local.repository.CurrentWeatherRepository
import com.example.jakartaweather.data.local.repository.DailyWeatherRepository
import com.example.jakartaweather.data.local.repository.HourlyWeatherRepository
import com.example.jakartaweather.utils.CommonCons
import com.example.jakartaweather.utils.WeatherConverter.getWeatherCondition
import com.example.jakartaweather.utils.WeatherConverter.getWeatherIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val currentWeather: ObservableField<String> = ObservableField("")
    val currentLocation: ObservableField<String> = ObservableField("")
    val currentDate: ObservableField<String> = ObservableField("")
    val currentWeatherIcon: ObservableField<Int> = ObservableField()
    val currentWeatherCondition: ObservableField<String> = ObservableField()

    private val currentDao: WeatherCurrentDAO =
        WeatherRoomDatabase.getDatabase(application.applicationContext).weatherCurrentDao()
    private val hourlyDao: WeatherHourlyDAO =
        WeatherRoomDatabase.getDatabase(application.applicationContext).weatherHourlyDao()
    private val dailyDAO: WeatherDailyDAO =
        WeatherRoomDatabase.getDatabase(application.applicationContext).weatherDailyDao()

    private val currentRepository: CurrentWeatherRepository = CurrentWeatherRepository(currentDao)
    private val hourlyRepository: HourlyWeatherRepository = HourlyWeatherRepository(hourlyDao)
    private val dailyRepository: DailyWeatherRepository = DailyWeatherRepository(dailyDAO)

    val currentWeatherLiveData: LiveData<WeatherDataCurrentEntity>? =
        currentRepository.currentWeather
    val hourlyWeatherLiveData: LiveData<List<WeatherDataHourlyEntity>> =
        hourlyRepository.hourlyWeather
    val dailyWeatherLiveData: LiveData<List<WeatherDataDailyEntity>> =
        dailyRepository.dailyWeather

    private val indonesia = Locale("id", "ID", "ID")
    private val simpleDateFormat = SimpleDateFormat(
        CommonCons.DATE_FORMAT, indonesia
    )

    init {
        simpleDateFormat.timeZone = TimeZone.getTimeZone(CommonCons.DATE_TIMEZONE)
    }


    fun getCurrentWeather() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val currentWeather = currentRepository.getCurrentWeather()
                val hourlyWeather = hourlyRepository.getHourlyWeather()
                val dailyWeather = dailyRepository.getDailyWeather()

                val date = hourlyWeather.list.map {
                    SimpleDateFormat(
                        "hh a", Locale.ENGLISH
                    ).format(it.dateTime * 1000)
                }

                val days = dailyWeather.list.map {
                    SimpleDateFormat(
                        "EEEE", indonesia
                    ).format(it.dateTime * 1000)
                }

                for (x in 0 until date.count()) {
                    hourlyWeather.list[x].id = date[x]
                }

                for (x in 0 until date.count()) {
                    dailyWeather.list[x].id = days[x]
                }

                insertCurrentWeather(currentWeather)
                insertHourlyWeather(hourlyWeather.list)
                insertDailyWeather(dailyWeather.list)
            } catch (e: Exception) {
                Log.d("error", e.message ?: "")
            }
        }
    }

    fun setCurrentWeather(weather: WeatherDataCurrentEntity) {
        weather.main?.let {
            currentWeather.set("${it.temperature?.roundToInt()}°")
        }
        weather.dateTime?.let {
            currentDate.set(simpleDateFormat.format(it * 1000))
            currentLocation.set(weather.name)
        }
        weather.weather?.let {
            setWeather(it.first())
        }
    }

    private fun setWeather(weather: WeatherModel) {
        val strings = getWeatherCondition(weather.icon ?: "")
        currentWeatherIcon.set(getWeatherIcon(weather.icon ?: ""))
        currentWeatherCondition.set(strings)
    }


    private fun insertCurrentWeather(item: WeatherDataCurrentEntity) {
        currentRepository.insert(item)
    }

    private fun insertHourlyWeather(datas: List<WeatherDataHourlyEntity>) {
        val data = datas.take(8)
        hourlyRepository.insertAll(data)
    }

    private fun insertDailyWeather(datas: List<WeatherDataDailyEntity>) {
        dailyRepository.insertAll(datas)
    }

}