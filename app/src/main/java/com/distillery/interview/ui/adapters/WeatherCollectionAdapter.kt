package com.distillery.interview.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.fragments.WeatherObjectFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = VIEWPAGER_CANT_TABS

    override fun createFragment(position: Int): Fragment {
        val fragment = WeatherObjectFragment()
        fragment.arguments = Bundle().apply {
            when (position) {
                CURRENT_WEATHER_SCREEN -> {
                    //TODO: Add fragments according position
                }
                TODAY_WEATHER_SCREEN -> {
                    //TODO: Add fragments according position
                }
                NEXT_DAYS_WEATHER_SCREEN -> {
                    //TODO: Add fragments according position
                }
            }
        }
        return fragment
    }

    companion object {
        private const val VIEWPAGER_CANT_TABS = 3
        const val CURRENT_WEATHER_SCREEN = 0
        const val TODAY_WEATHER_SCREEN = 1
        const val NEXT_DAYS_WEATHER_SCREEN = 2
    }
}