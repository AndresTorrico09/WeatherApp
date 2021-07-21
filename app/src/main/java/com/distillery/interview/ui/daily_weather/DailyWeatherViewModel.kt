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

        runCatching {
            weatherRepository.getDailyWeather()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Error(it))
        }
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

