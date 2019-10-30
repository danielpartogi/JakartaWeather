package com.example.jakartaweather.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jakartaweather.R
import com.example.jakartaweather.data.local.entity.WeatherDataDailyEntity
import com.example.jakartaweather.databinding.ItemMainRecyclerDailyBinding
import com.example.jakartaweather.utils.WeatherConverter
import kotlin.math.roundToInt

class MainDailyAdapter internal constructor(
) : RecyclerView.Adapter<MainDailyAdapter.DailyViewHolder>() {

    private var daily = emptyList<WeatherDataDailyEntity>().toMutableList()

    class DailyViewHolder(val binding: ItemMainRecyclerDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: WeatherDataDailyEntity) {
            binding.itemDailyDays.text = item.id
            item.weather?.first()?.let {
                binding.itemDailyIcon.setImageResource(
                    WeatherConverter.getWeatherIconGrey(
                        it.icon ?: ""
                    )
                )
            }
            item.main.temperature?.let {
                binding.itemDailyDegree.text = "${it.roundToInt()}°"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val binding = DataBindingUtil
            .inflate<ItemMainRecyclerDailyBinding>(
                LayoutInflater.from(parent.context), R.layout.item_main_recycler_daily,
                parent, false
            )
        return DailyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val current = daily[position]
        holder.bind(current)

        holder.binding.executePendingBindings()

    }

    override fun getItemCount() = daily.size

    internal fun setDaily(items: List<WeatherDataDailyEntity>) {
        this.daily = items.toMutableList()
        notifyDataSetChanged()
    }
}