package com.example.jakartaweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakartaweather.R
import com.example.jakartaweather.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mMainActivityBinding: ActivityMainBinding
    private lateinit var mAdapterHourly: MainHourlyAdapter
    private lateinit var mAdapterDaily: MainDailyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
        initObserveAble()
    }

    private fun initView() {

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mMainActivityBinding.viewModel = mViewModel
        mViewModel.getCurrentWeather()

        mAdapterHourly = MainHourlyAdapter()
        mAdapterDaily = MainDailyAdapter()

        main_recycler_hourly.adapter = mAdapterHourly
        main_recycler_daily.adapter = mAdapterDaily
        main_recycler_hourly.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        main_recycler_daily.layoutManager = LinearLayoutManager(this)
    }

    private fun initObserveAble() {
        mViewModel.currentWeatherLiveData?.observe(this, Observer { weather ->
            weather?.let {
                mViewModel.setCurrentWeather(it)
            }
        })

        mViewModel.hourlyWeatherLiveData.observe(this, Observer { hourly ->
            hourly?.let {
                mAdapterHourly.setHourly(it)
            }
        })

        mViewModel.dailyWeatherLiveData.observe(this, Observer {daily->
            daily?.let {
                mAdapterDaily.setDaily(it)
            }
        })
    }
}
