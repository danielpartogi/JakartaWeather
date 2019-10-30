package com.example.jakartaweather.utils

import com.example.jakartaweather.App
import com.example.jakartaweather.R

object WeatherConverter {

    fun getWeatherIcon(iconCode:String): Int{
        return with(iconCode) {
            when {
                contains("01") -> R.drawable.ic_sun
                contains("02")->R.drawable.ic_cloud_cloudy_white
                contains("03")->R.drawable.ic_cloud_cloudy_white
                contains("04")->R.drawable.ic_cloud_cloudy_white
                contains("09") -> R.drawable.ic_cloud_drizzle_white
                contains("10") -> R.drawable.ic_cloud_rain_white
                contains("11") -> R.drawable.ic_cloud_lightning_white
                else -> R.drawable.ic_cloud_cloudy_white
            }
        }
    }

    fun getWeatherIconGrey(iconCode:String): Int{
        return with(iconCode) {
            when {
                contains("01") -> R.drawable.ic_sun
                contains("02")->R.drawable.ic_cloud_cloudy_grey
                contains("03")->R.drawable.ic_cloud_cloudy_grey
                contains("04")->R.drawable.ic_cloud_cloudy_grey
                contains("09") -> R.drawable.ic_cloud_drizzle_grey
                contains("10") -> R.drawable.ic_cloud_rain_grey
                contains("11") -> R.drawable.ic_cloud_lightning_grey
                else -> R.drawable.ic_cloud_cloudy_white
            }
        }
    }

    fun getWeatherCondition(iconCode: String):String{
        return with(iconCode) {
            when {
                contains("01") -> App.getContext()?.getString(R.string.cloudy)?:""
                contains("02")->App.getContext()?.getString(R.string.cloudy)?:""
                contains("03")->App.getContext()?.getString(R.string.cloudy)?:""
                contains("04")->App.getContext()?.getString(R.string.cloudy)?:""
                contains("09") -> App.getContext()?.getString(R.string.drizzle)?:""
                contains("10") -> App.getContext()?.getString(R.string.rain)?:""
                contains("11") -> App.getContext()?.getString(R.string.thunder)?:""
                else -> App.getContext()?.getString(R.string.cloudy)?:""
            }
        }
    }
}