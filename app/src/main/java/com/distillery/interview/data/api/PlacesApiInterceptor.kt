package com.distillery.interview.data.api

import okhttp3.Interceptor
import okhttp3.Response

class PlacesApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder().build()

        val request = originalRequest.newBuilder()
            .url(url)
            .addHeader("X-Algolia-Application-Id", PlacesAPI.PLACES_APP_ID)
            .addHeader("X-Algolia-API-Key", PlacesAPI.PLACES_API_KEY)
            .build()

        return chain.proceed(request)
    }
}