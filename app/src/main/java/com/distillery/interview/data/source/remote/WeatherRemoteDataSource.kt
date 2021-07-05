package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherDataSource

object WeatherRemoteDataSource : WeatherDataSource {

    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    override suspend fun getCurrentWeather(): Result<CurrentWeatherResponse> =
        Result.Success(weatherApi.getCurrentWeather())

    override suspend fun getHourlyWeather(): Result<HourlyWeatherResponse> =
        Result.Success(weatherApi.getHourlyWeather())

    override suspend fun getDailyWeather(): Result<DailyWeatherResponse> =
        Result.Success(weatherApi.getDailyWeather())
}