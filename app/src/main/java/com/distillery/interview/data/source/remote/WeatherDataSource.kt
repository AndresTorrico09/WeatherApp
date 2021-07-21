package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse

interface WeatherDataSource {
    suspend fun getCurrentWeather(): CurrentWeatherResponse

    suspend fun getHourlyWeather(): HourlyWeatherResponse

    suspend fun getDailyWeather(): DailyWeatherResponse

} 