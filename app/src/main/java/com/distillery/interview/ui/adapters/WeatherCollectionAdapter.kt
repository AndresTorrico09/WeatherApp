package com.distillery.interview.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.fragments.CurrentWeatherFragment
import com.distillery.interview.ui.fragments.NextDaysFragment
import com.distillery.interview.ui.fragments.TodayWeatherFragment

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