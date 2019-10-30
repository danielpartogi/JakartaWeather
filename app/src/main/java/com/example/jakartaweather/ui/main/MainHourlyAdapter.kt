package com.example.jakartaweather.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jakartaweather.R
import com.example.jakartaweather.data.local.entity.WeatherDataHourlyEntity
import com.example.jakartaweather.databinding.ItemMainRecyclerHourlyBinding
import com.example.jakartaweather.utils.WeatherConverter
import kotlin.math.roundToInt

class MainHourlyAdapter internal constructor(
) : RecyclerView.Adapter<MainHourlyAdapter.HourlyViewHolder>() {

    private var hourly = emptyList<WeatherDataHourlyEntity>().toMutableList()

    class HourlyViewHolder(val binding: ItemMainRecyclerHourlyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: WeatherDataHourlyEntity) {
            binding.itemMainHour.text = item.id
            item.weather?.first()?.let {
                binding.itemIconWeather.setImageResource(
                    WeatherConverter.getWeatherIconGrey(
                        it.icon ?: ""
                    )
                )
            }
            item.main.temperature?.let {
                binding.itemDegree.text = "${it.roundToInt()}°"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val binding = DataBindingUtil
            .inflate<ItemMainRecyclerHourlyBinding>(
                LayoutInflater.from(parent.context), R.layout.item_main_recycler_hourly,
                parent, false
            )
        return HourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val current = hourly[position]
        holder.bind(current)

        holder.binding.executePendingBindings()

    }

    override fun getItemCount() = hourly.size

    internal fun setHourly(items: List<WeatherDataHourlyEntity>) {
        this.hourly = items.toMutableList()
        notifyDataSetChanged()
    }
}