package com.distillery.interview.ui.hourly_weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.distillery.interview.R
import com.distillery.interview.data.models.Hourly
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.data.source.remote.WeatherRemoteDataSource
import com.distillery.interview.databinding.FragmentHourlyWeatherBinding
import com.distillery.interview.ui.MainSharedViewModel
import com.distillery.interview.util.toDate

class HourlyWeatherFragment : Fragment(R.layout.fragment_hourly_weather) {
    private val viewModel: HourlyWeatherViewModel by viewModels {
        HourlyWeatherViewModel.Factory(
            WeatherRepository(
                WeatherRemoteDataSource()
            )
        )
    }
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHourlyWeatherBinding
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHourlyWeatherBinding.bind(view)

        mainSharedViewModel.locationLiveData.observe(viewLifecycleOwner, {
            viewModel.getHourlyWeather(it.lat, it.lon).observe(viewLifecycleOwner, resultObserver)
        })

        setupRecyclerView()
    }

    private val resultObserver: Observer<Result<HourlyWeatherResponse>> =
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

    private fun setValues(hourlyWeatherResponse: HourlyWeatherResponse) {
        binding.dateToday.text = hourlyWeatherResponse.hourly[0].dt.toDate()
        hourlyWeatherAdapter.setItems(hourlyWeatherResponse.hourly as ArrayList<Hourly>)
    }

    private fun setupRecyclerView() {
        hourlyWeatherAdapter = HourlyWeatherAdapter()
        binding.rvHourlyWeather.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvHourlyWeather.adapter = hourlyWeatherAdapter
    }

    private fun showError(err: String) =
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()

    private fun showLoading() =
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()

    private fun hideLoading() =
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()
}