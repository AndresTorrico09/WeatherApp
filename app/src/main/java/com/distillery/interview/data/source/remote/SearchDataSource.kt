package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.SearchResponse

interface SearchDataSource {
    suspend fun getCities(newText: String?): SearchResponse
} 