package com.distillery.interview.data.api

import com.distillery.interview.data.api.WeatherAPI.Companion.WEATHER_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class WeatherApiInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "apiKey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, WEATHER_API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}