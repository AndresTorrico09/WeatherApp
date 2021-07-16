package com.distillery.interview.ui.daily_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository

class DailyWeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    fun getDailyWeather() = liveData {
        emit(Result.Loading())
        emit(weatherRepository.getDailyWeather())
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

