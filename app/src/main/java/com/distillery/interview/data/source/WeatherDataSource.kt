package com.distillery.interview.data.source

import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result

interface WeatherDataSource {
    suspend fun getCurrentWeather(): Result<CurrentWeatherResponse>

    suspend fun getHourlyWeather(): Result<HourlyWeatherResponse>

    suspend fun getDailyWeather(): Result<DailyWeatherResponse>
}