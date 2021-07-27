package com.distillery.interview.data.source.remote

import com.distillery.interview.data.models.City
import com.distillery.interview.data.models.SearchResponse
import java.util.*

class SearchLocalDataSource : SearchDataSource {
    override suspend fun getCities(newText: String?): SearchResponse {
        //TODO: change source of fake data (Room/DataStore)
        val listCities = arrayListOf(
            City("Argentine", "Buenos Aires", 0),
            City("Brazil", "Rio de Janeiro", 1),
            City("United States", "New York", 2),
            City("England", "London", 3),
            City("France", "Paris", 4),
        )

        return if (newText.isNullOrEmpty()) {
            SearchResponse(arrayListOf())
        } else {
            SearchResponse(
                listCities.filter { city ->
                    city.name.lowercase(Locale.getDefault()).contains(newText.toString())
                }
            )
        }
    }
}