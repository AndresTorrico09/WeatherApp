package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyProvider
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSource : WeatherDataSource {
    private val weatherApi = DependencyProvider.provideService(WeatherAPI::class.java)

    override suspend fun getCurrentWeather(): Result<CurrentWeatherResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(weatherApi.getCurrentWeather())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getHourlyWeather(): Result<HourlyWeatherResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(weatherApi.getHourlyWeather())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getDailyWeather(): Result<DailyWeatherResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(weatherApi.getDailyWeather())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}