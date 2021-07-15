package com.distillery.interview.ui.hourly_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository

class HourlyWeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    fun getHourlyWeather() = liveData {
        emit(Result.Loading())
        emit(weatherRepository.getHourlyWeather())
    }

    class Factory(
        private val weatherRepository: WeatherRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(
            modelClass: Class<T>,
        ): T {
            return modelClass.getConstructor(WeatherRepository::class.java)
                .newInstance(weatherRepository)
        }
    }
}

