package com.distillery.interview.data.source.remote

import com.distillery.interview.data.DependencyPlacesProvider
import com.distillery.interview.data.api.PlacesAPI
import com.distillery.interview.data.models.SearchBodyRequest
import com.distillery.interview.data.models.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteDataSource : SearchDataSource {
    private val placesAPI = DependencyPlacesProvider.provideService(PlacesAPI::class.java)

    override suspend fun getCities(searchBodyRequest: SearchBodyRequest): SearchResponse =
        withContext(Dispatchers.IO) { placesAPI.getCities(searchBodyRequest) }
}