package com.distillery.interview.data.source

import com.distillery.interview.data.models.SearchBodyRequest
import com.distillery.interview.data.models.SearchResponse
import com.distillery.interview.data.source.remote.SearchDataSource

class SearchRepository(private val searchRemoteDataSource: SearchDataSource) {

    suspend fun getCities(searchBodyRequest: SearchBodyRequest): SearchResponse =
        searchRemoteDataSource.getCities(searchBodyRequest)
}
