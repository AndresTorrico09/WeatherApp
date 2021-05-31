package com.distillery.interview.data

import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result

class WeatherRepository {
    //TODO: Provide this dependency via constructor
    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    suspend fun getCurrentWeather(): Result<CurrentWeatherResponse> =
        Result.Success(weatherApi.getCurrentWeather())

    suspend fun getHourlyWeather(): Result<HourlyWeatherResponse> =
        Result.Success(weatherApi.getHourlyWeather())

    suspend fun getDailyWeather(): Result<DailyWeatherResponse> =
        Result.Success(weatherApi.getDailyWeather())
}