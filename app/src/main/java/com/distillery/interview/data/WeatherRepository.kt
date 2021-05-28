package com.distillery.interview.data

import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.*

class WeatherRepository {
    //TODO: Provide this dependency via constructor
    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    suspend fun getCurrentWeather(): Result<CurrentWeatherResponse> =
        Result.Success(weatherApi.getCurrentWeather())

    suspend fun getHourlyWeather(): Result<HourlyWeatherResponse> =
        Result.Success(weatherApi.getHourlyWeather())
}