package com.distillery.interview.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.distillery.interview.ui.fragments.ARG_OBJECT
import com.distillery.interview.ui.fragments.WeatherObjectFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = WeatherObjectFragment()
        fragment.arguments = Bundle().apply {
            when (position){
                0 -> {
                    putString(ARG_OBJECT, "Current Weather Title")
                }
                1 -> {
                    putString(ARG_OBJECT, "Today Weather Title")
                }
                2 -> {
                    putString(ARG_OBJECT, "5 Days Title")
                }
            }
        }
        return fragment
    }
}