package com.distillery.interview.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.current_weather.CurrentWeatherFragment
import com.distillery.interview.ui.daily_weather.DailyFragment
import com.distillery.interview.ui.hourly_weather.HourlyWeatherFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = VIEWPAGER_CANT_TABS

    override fun createFragment(position: Int) = when (position) {
        CURRENT_WEATHER_SCREEN -> {
            CurrentWeatherFragment()
        }
        HOURLY_WEATHER_SCREEN -> {
            HourlyWeatherFragment()
        }
        DAILY_WEATHER_SCREEN -> {
            DailyFragment()
        }
        else -> CurrentWeatherFragment()
    }

    companion object {
        private const val VIEWPAGER_CANT_TABS = 3
        const val CURRENT_WEATHER_SCREEN = 0
        const val HOURLY_WEATHER_SCREEN = 1
        const val DAILY_WEATHER_SCREEN = 2
    }
}