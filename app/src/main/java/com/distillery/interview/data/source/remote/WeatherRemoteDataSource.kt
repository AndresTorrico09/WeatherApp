package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSource : WeatherDataSource {

    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    override suspend fun getCurrentWeather(): Result<WeatherResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(weatherApi.getCurrentWeather())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}