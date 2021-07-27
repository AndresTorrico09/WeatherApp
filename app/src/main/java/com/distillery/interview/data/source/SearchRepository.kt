package com.distillery.interview.data.source

import com.distillery.interview.data.models.SearchResponse
import com.distillery.interview.data.source.remote.SearchDataSource

class SearchRepository(private val searchLocalDataSource: SearchDataSource) {

    suspend fun getCities(newText: String?): SearchResponse =
        searchLocalDataSource.getCities(newText)
}
