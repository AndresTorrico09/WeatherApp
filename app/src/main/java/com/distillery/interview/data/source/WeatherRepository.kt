package com.distillery.interview.data.source

import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.data.source.remote.WeatherDataSource

class WeatherRepository(private val weatherRemoteDataSource: WeatherDataSource) {

    suspend fun getCurrentWeather(): Result<WeatherResponse> =
        weatherRemoteDataSource.getCurrentWeather()
}
