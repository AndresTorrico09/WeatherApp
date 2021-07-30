package com.distillery.interview.data

import com.distillery.interview.data.api.PlacesAPI
import com.distillery.interview.data.api.PlacesApiInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyPlacesProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(PlacesAPI.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    private val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    private val placesInterceptor by lazy { PlacesApiInterceptor() }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(placesInterceptor)
            .build()
    }

    fun <T> provideService(service: Class<T>): T {
        return retrofit.create(service)
    }
}