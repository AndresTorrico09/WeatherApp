package com.distillery.interview.ui.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.current_weather.CurrentWeatherFragment
import com.distillery.interview.ui.next_days_weather.NextDaysFragment
import com.distillery.interview.ui.today_weather.TodayWeatherFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = VIEWPAGER_CANT_TABS

    override fun createFragment(position: Int) = when (position) {
        CURRENT_WEATHER_SCREEN -> {
            CurrentWeatherFragment()
        }
        TODAY_WEATHER_SCREEN -> {
            TodayWeatherFragment()
        }
        NEXT_DAYS_WEATHER_SCREEN -> {
            NextDaysFragment()
        }
        else -> CurrentWeatherFragment()
    }

    companion object {
        private const val VIEWPAGER_CANT_TABS = 3
        const val CURRENT_WEATHER_SCREEN = 0
        const val TODAY_WEATHER_SCREEN = 1
        const val NEXT_DAYS_WEATHER_SCREEN = 2
    }
}