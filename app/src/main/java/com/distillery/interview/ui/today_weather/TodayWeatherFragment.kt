package com.distillery.interview.ui.today_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.distillery.interview.data.CoroutinesDispatcherProvider
import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.WeatherRepository
import com.distillery.interview.data.models.Hourly
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.databinding.FragmentTodayWeatherBinding
import com.distillery.interview.util.toDate

class TodayWeatherFragment : Fragment() {

    private val weatherRepository =
        DependencyProvider.provideRepository<WeatherRepository>()
    private val coroutinesDispatcherProvider =
        DependencyProvider.provideCoroutinesDispatcherProvider<CoroutinesDispatcherProvider>()
    private val viewModelFactory =
        TodayWeatherViewModel.Factory(this, weatherRepository, coroutinesDispatcherProvider)
    private val viewModel: TodayWeatherViewModel by activityViewModels { viewModelFactory }
    private lateinit var binding: FragmentTodayWeatherBinding
    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiState.observe(viewLifecycleOwner, { weatherResponse ->
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
                    showError(weatherResponse.errors.first())
                }
            }
        })
        viewModel.getTodayHourlyWeather()

        setupRecyclerView()
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

    private fun showError(err: String) {
        //TODO: Add custom error showing
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        //TODO: Add custom loading
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()
    }

    private fun hideLoading() {
        //TODO: Add custom loading
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()
    }
}