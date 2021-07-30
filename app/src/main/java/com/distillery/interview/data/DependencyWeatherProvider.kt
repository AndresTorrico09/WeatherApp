package com.distillery.interview.data

import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.api.WeatherApiInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyWeatherProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WeatherAPI.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    private val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    private val apiKeyInterceptor by lazy { WeatherApiInterceptor() }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    fun <T> provideService(service: Class<T>): T {
        return retrofit.create(service)
    }
}