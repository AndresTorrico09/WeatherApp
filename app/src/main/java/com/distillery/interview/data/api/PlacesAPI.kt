package com.distillery.interview.data.api

import com.distillery.interview.BuildConfig
import com.distillery.interview.data.models.SearchBodyRequest
import com.distillery.interview.data.models.SearchResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesAPI {

    @POST("1/places/query")
    suspend fun getCities(
        @Body query: SearchBodyRequest,
        @Query("language") units: String = LANGUAGE,
    ): SearchResponse

    companion object {
        const val BASE_URL = "https://places-dsn.algolia.net/"
        const val PLACES_API_KEY = BuildConfig.PLACES_API_KEY
        const val PLACES_APP_ID = BuildConfig.PLACES_APP_ID
        private const val LANGUAGE = "en"
    }
}