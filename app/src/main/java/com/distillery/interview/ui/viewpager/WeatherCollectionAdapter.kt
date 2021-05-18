package com.distillery.interview.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.current_weather.CurrentWeatherFragment
import com.distillery.interview.ui.next_days_weather.NextDaysFragment
import com.distillery.interview.ui.today_weather.TodayWeatherFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                CurrentWeatherFragment()
            }
            1 -> {
                TodayWeatherFragment()
            }
            2 -> {
                NextDaysFragment()
            }
            else -> Fragment()
        }
    }
}