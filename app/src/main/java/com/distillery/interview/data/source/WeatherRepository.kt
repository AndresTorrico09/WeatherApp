package com.distillery.interview.data.source

import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.source.remote.WeatherDataSource

class WeatherRepository(private val weatherRemoteDataSource: WeatherDataSource) {

    suspend fun getCurrentWeather(): CurrentWeatherResponse =
        weatherRemoteDataSource.getCurrentWeather()

    suspend fun getHourlyWeather(): HourlyWeatherResponse =
        weatherRemoteDataSource.getHourlyWeather()

    suspend fun getDailyWeather(): DailyWeatherResponse =
        weatherRemoteDataSource.getDailyWeather()
}
