package com.distillery.interview.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.distillery.interview.R
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.CURRENT_WEATHER_SCREEN
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.NEXT_DAYS_WEATHER_SCREEN
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.TODAY_WEATHER_SCREEN
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WeatherCollectionFragment : Fragment() {
    private lateinit var weatherCollectionAdapter: WeatherCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.collection_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherCollectionAdapter = WeatherCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = weatherCollectionAdapter

        setTabLayout(view)
    }

    private fun setTabLayout(view: View) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                CURRENT_WEATHER_SCREEN -> {
                    tab.text = getString(R.string.current_title)
                }
                TODAY_WEATHER_SCREEN -> {
                    tab.text = getString(R.string.today_title)
                }
                NEXT_DAYS_WEATHER_SCREEN -> {
                    tab.text = getString(R.string.next_days_title)
                }
            }
        }.attach()
    }
}
