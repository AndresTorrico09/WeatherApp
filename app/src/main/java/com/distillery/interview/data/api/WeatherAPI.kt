package com.distillery.interview.data.api

import com.distillery.interview.BuildConfig
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String = UNIT_METRIC,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY,
    ): CurrentWeatherResponse

    @GET("data/2.5/onecall")
    suspend fun getHourlyWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String = UNIT_METRIC,
        @Query("exclude") exclude: String = EXCLUDE_HOURLY,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY,
    ): HourlyWeatherResponse

    @GET("data/2.5/onecall")
    suspend fun getDailyWeather(
        @Query("lat") lat: Double = LAT_BSAS,
        @Query("lon") lon: Double = LON_BSAS,
        @Query("units") units: String = UNIT_METRIC,
        @Query("exclude") exclude: String = EXCLUDE_DAILY,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY,
    ): DailyWeatherResponse

    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY
        private const val UNIT_METRIC = "metric"
        private const val LAT_BSAS = -34.603722
        private const val LON_BSAS = -58.381592
        private const val EXCLUDE_HOURLY = "current,minutely,daily,alerts"
        private const val EXCLUDE_DAILY = "current,minutely,hourly,alerts"
    }
}