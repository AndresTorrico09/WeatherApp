package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyWeatherProvider
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSource : WeatherDataSource {
    private val weatherApi = DependencyWeatherProvider.provideService(WeatherAPI::class.java)

    override suspend fun getCurrentWeather(lat: Double?, lon: Double?): CurrentWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getCurrentWeather(lat = lat, lon = lon) }

    override suspend fun getHourlyWeather(lat: Double?, lon: Double?): HourlyWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getHourlyWeather(lat = lat, lon = lon) }

    override suspend fun getDailyWeather(lat: Double?, lon: Double?): DailyWeatherResponse =
        withContext(Dispatchers.IO) { weatherApi.getDailyWeather(lat = lat, lon = lon) }
}