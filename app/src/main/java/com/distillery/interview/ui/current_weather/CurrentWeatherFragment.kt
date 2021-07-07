package com.distillery.interview.ui.current_weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.distillery.interview.R
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.data.source.remote.WeatherRemoteDataSource
import com.distillery.interview.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment : Fragment(R.layout.fragment_current_weather) {

    private val viewModel: CurrentWeatherViewModel by viewModels {
        CurrentWeatherViewModel.Factory(
            WeatherRepository(
                WeatherRemoteDataSource()
            )
        )
    }
    private lateinit var binding: FragmentCurrentWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentWeatherBinding.bind(view)

        viewModel.getCurrentWeather().observe(viewLifecycleOwner, { weatherResponse ->
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
        })
    }

    private fun setValues(weatherResponse: WeatherResponse) {
        binding.apply {
            with(weatherResponse) {
                description.text = weather.firstOrNull()?.main ?: ""
                tempMax.text = getString(R.string.max_temp_text, main.temp_max.toString())
                tempMin.text = getString(R.string.min_temp_text, main.temp_min.toString())
                temp.text = getString(R.string.temp_text, main.temp.toString())
                feelsLike.text = getString(R.string.feels_like_text, main.feels_like.toString())
            }
        }
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