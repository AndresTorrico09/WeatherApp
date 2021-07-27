package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.City
import com.distillery.interview.data.models.SearchResponse

class SearchLocalDataSource : SearchDataSource {
    override suspend fun getCities(): SearchResponse =
        SearchResponse(arrayListOf(
            City("Argentine", "Buenos Aires", 1),
            City("Brazil", "Rio de Janeiro", 2))
        )
}