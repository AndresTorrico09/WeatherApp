package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse

interface WeatherDataSource {
    suspend fun getCurrentWeather(): Result<WeatherResponse>
} 