package com.distillery.interview.data.models

data class SearchResponse(
    val cities: List<City>,
)

data class City(
    val country: String,
    val name: String,
    val id: Int,
)