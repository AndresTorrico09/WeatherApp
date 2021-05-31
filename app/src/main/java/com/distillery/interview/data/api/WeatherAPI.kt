package com.distillery.interview.data.api

import com.distillery.interview.BuildConfig
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.HourlyWeatherResponse
import retrofit2.http.GET

interface WeatherAPI {

    @GET("data/2.5/weather?units=metric&q=$LOCATION&appid=$WEATHER_API_KEY")
    suspend fun getCurrentWeather(): CurrentWeatherResponse

    @GET("data/2.5/onecall?units=metric&lat=$LAT_BSAS&lon=$LON_BSAS&exclude=current,minutely,daily,alerts&appid=$WEATHER_API_KEY")
    suspend fun getHourlyWeather(): HourlyWeatherResponse

    @GET("data/2.5/onecall?units=metric&lat=$LAT_BSAS&lon=$LON_BSAS&exclude=current,minutely,hourly,alerts&appid=$WEATHER_API_KEY")
    suspend fun getDailyWeather(): DailyWeatherResponse

    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY
        private const val LOCATION = "Buenos Aires"
        private const val LAT_BSAS = -34.603722
        private const val LON_BSAS = -58.381592
    }
}