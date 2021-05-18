package com.distillery.interview.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.fragments.WeatherObjectFragment
import com.distillery.interview.util.Constants.CURRENT_WEATHER_SCREEN
import com.distillery.interview.util.Constants.NEXT_DAYS_WEATHER_SCREEN
import com.distillery.interview.util.Constants.TODAY_WEATHER_SCREEN
import com.distillery.interview.util.Constants.VIEWPAGER_CANT_TABS

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = VIEWPAGER_CANT_TABS

    override fun createFragment(position: Int): Fragment {
        val fragment = WeatherObjectFragment()
        fragment.arguments = Bundle().apply {
            when (position){
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
}