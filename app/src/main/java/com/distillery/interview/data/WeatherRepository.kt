package com.distillery.interview.data

import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.data.models.*

class WeatherRepository {
    //TODO: Provide this dependency via constructor
    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    suspend fun getCurrentWeather(): Result<WeatherResponse> =
        Result.Success(weatherApi.getCurrentWeather())
}