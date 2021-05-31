package com.distillery.interview.ui.daily_weather

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
import com.distillery.interview.data.models.Daily
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.databinding.FragmentDailyWeatherBinding

class DailyWeatherFragment : Fragment() {

    private val weatherRepository =
        DependencyProvider.provideRepository<WeatherRepository>()
    private val coroutinesDispatcherProvider =
        DependencyProvider.provideCoroutinesDispatcherProvider<CoroutinesDispatcherProvider>()
    private val viewModelFactory =
        DailyWeatherViewModel.Factory(this, weatherRepository, coroutinesDispatcherProvider)
    private val viewModel: DailyWeatherViewModel by activityViewModels { viewModelFactory }
    private lateinit var binding: FragmentDailyWeatherBinding
    private lateinit var dailyWeatherAdapter: DailyWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyWeatherBinding.inflate(inflater, container, false)
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
        viewModel.getDailyWeather()

        setupRecyclerView()
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