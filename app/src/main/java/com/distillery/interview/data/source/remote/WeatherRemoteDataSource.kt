package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSource : WeatherDataSource {
    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    override suspend fun getCurrentWeather(lat: Double?, lon: Double?): CurrentWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getCurrentWeather(lat = lat, lon = lon) }

    override suspend fun getHourlyWeather(lat: Double?, lon: Double?): HourlyWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getHourlyWeather(lat = lat, lon = lon) }

    override suspend fun getDailyWeather(): DailyWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getDailyWeather() }
}