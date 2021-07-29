package com.distillery.interview.data.source

import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.source.remote.WeatherDataSource

class WeatherRepository(private val weatherRemoteDataSource: WeatherDataSource) {

    suspend fun getCurrentWeather(lat: Double?, lon: Double?): CurrentWeatherResponse =
        weatherRemoteDataSource.getCurrentWeather(lat, lon)

    suspend fun getHourlyWeather(lat: Double?, lon: Double?): HourlyWeatherResponse =
        weatherRemoteDataSource.getHourlyWeather(lat, lon)

    suspend fun getDailyWeather(lat: Double?, lon: Double?): DailyWeatherResponse =
        weatherRemoteDataSource.getDailyWeather(lat, lon)
}
