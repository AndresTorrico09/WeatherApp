package com.distillery.interview.ui.daily_weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.distillery.interview.R
import com.distillery.interview.data.models.Daily
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.data.source.remote.WeatherRemoteDataSource
import com.distillery.interview.databinding.FragmentDailyWeatherBinding
import com.distillery.interview.ui.MainSharedViewModel

class DailyWeatherFragment : Fragment(R.layout.fragment_daily_weather) {

    private val viewModel: DailyWeatherViewModel by viewModels {
        DailyWeatherViewModel.Factory(
            WeatherRepository(
                WeatherRemoteDataSource()
            )
        )
    }
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentDailyWeatherBinding
    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDailyWeatherBinding.bind(view)

        mainSharedViewModel.locationLiveData.observe(viewLifecycleOwner, {
            viewModel.getDailyWeather(it.lat, it.lon).observe(viewLifecycleOwner, resultObserver)
        })

        setupRecyclerView()
    }

    private val resultObserver: Observer<Result<DailyWeatherResponse>> =
        Observer { weatherResponse ->
            when (weatherResponse) {
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Success -> {
                    hideLoading()
                    setValues(weatherResponse.data)
                }
                is Result.Error -> {
                    hideLoading()
                    showError(weatherResponse.exception?.message.toString())
                }
            }
        }

    private fun setValues(dailyWeatherResponse: DailyWeatherResponse) {
        dailyWeatherAdapter.setItems(dailyWeatherResponse.daily as ArrayList<Daily>)
    }

    private fun setupRecyclerView() {
        dailyWeatherAdapter = DailyWeatherAdapter()
        binding.rvDailyWeather.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvDailyWeather.adapter = dailyWeatherAdapter
    }

    private fun showError(err: String) =
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()

    private fun showLoading() =
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()

    private fun hideLoading() =
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()
}