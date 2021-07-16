package com.distillery.interview.ui.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.distillery.interview.R
import com.distillery.interview.databinding.FragmentCollectionWeatherBinding
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.CURRENT_WEATHER_SCREEN
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.DAILY_WEATHER_SCREEN
import com.distillery.interview.ui.viewpager.WeatherCollectionAdapter.Companion.HOURLY_WEATHER_SCREEN
import com.google.android.material.tabs.TabLayoutMediator

class WeatherCollectionFragment : Fragment(R.layout.fragment_collection_weather) {
    private lateinit var weatherCollectionAdapter: WeatherCollectionAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: FragmentCollectionWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCollectionWeatherBinding.bind(view)
        weatherCollectionAdapter = WeatherCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = weatherCollectionAdapter

        setTabLayout()
    }

    private fun setTabLayout() = TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
        when (position) {
            CURRENT_WEATHER_SCREEN -> {
                tab.text = getString(R.string.current_title)
            }
            HOURLY_WEATHER_SCREEN -> {
                tab.text = getString(R.string.today_title)
            }
            DAILY_WEATHER_SCREEN -> {
                tab.text = getString(R.string.next_days_title)
            }
        }
    }.attach()
}
