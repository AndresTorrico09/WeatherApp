package com.distillery.interview.ui.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.distillery.interview.R
import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.api.WeatherAPI

class CurrentWeatherFragment : Fragment() {

    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)
    private val viewModelFactory = CurrentWeatherViewModel.Factory(this, null, weatherApi)
    private val viewModel: CurrentWeatherViewModel by activityViewModels { viewModelFactory }

    private val currentWeatherObserver = Observer<CurrentWeatherViewModel.CurrentWeatherUiModel> {
        val uiModel = it ?: return@Observer

        uiModel.showError?.consume()?.let { err ->
            Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()
        }

        uiModel.showSuccess?.consume()?.let { response ->
            val desc = response.weather[0].description
            Toast.makeText(requireContext(), desc, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiState.observe(viewLifecycleOwner, currentWeatherObserver)
        viewModel.getCurrentWeather()
    }
}