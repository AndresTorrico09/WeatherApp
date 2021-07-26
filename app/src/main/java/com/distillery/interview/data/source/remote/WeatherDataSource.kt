package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse

interface WeatherDataSource {
    suspend fun getCurrentWeather(lat: Double?, lon: Double?): CurrentWeatherResponse

    suspend fun getHourlyWeather(lat: Double?, lon: Double?): HourlyWeatherResponse

    suspend fun getDailyWeather(): DailyWeatherResponse

} 