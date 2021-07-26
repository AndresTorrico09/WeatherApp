package com.distillery.interview.ui.current_weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.distillery.interview.R
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.data.source.remote.WeatherRemoteDataSource
import com.distillery.interview.databinding.FragmentCurrentWeatherBinding
import com.distillery.interview.ui.MainViewModel

class CurrentWeatherFragment : Fragment(R.layout.fragment_current_weather) {
    private val viewModel: CurrentWeatherViewModel by viewModels {
        CurrentWeatherViewModel.Factory(
            WeatherRepository(
                WeatherRemoteDataSource()
            )
        )
    }
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCurrentWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentWeatherBinding.bind(view)

        mainViewModel.locationLiveData.observe(viewLifecycleOwner, {
            viewModel.getCurrentWeather(it.lat, it.lon).observe(viewLifecycleOwner, resultObserver)
        })
    }

    private val resultObserver: Observer<Result<CurrentWeatherResponse>> =
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

    private fun setValues(weatherResponse: CurrentWeatherResponse) = binding.run {
        with(weatherResponse) {
            description.text = weather.firstOrNull()?.main ?: ""
            tempMax.text = getString(R.string.max_temp_text, main.temp_max.toString())
            tempMin.text = getString(R.string.min_temp_text, main.temp_min.toString())
            temp.text = getString(R.string.temp_text, main.temp.toString())
            feelsLike.text = getString(R.string.feels_like_text, main.feels_like.toString())
        }
    }

    private fun showError(err: String) =
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()

    private fun showLoading() =
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()

    private fun hideLoading() =
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()
}