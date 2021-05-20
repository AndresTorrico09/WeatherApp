package com.distillery.interview.data

import com.distillery.interview.data.api.ApiKeyInterceptor
import com.distillery.interview.data.api.WeatherAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WeatherAPI.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    private val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    private val apiKeyInterceptor by lazy { ApiKeyInterceptor() }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    fun <T> provideService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun <T> provideRepository(): WeatherRepository {
        return WeatherRepository()
    }

    fun <T> provideCoroutinesDispatcherProvider(): CoroutinesDispatcherProvider {
        return CoroutinesDispatcherProvider()
    }
}