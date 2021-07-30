package com.distillery.interview.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val hits: List<HitsItem?>? = null,
)

data class HitsItem(
    @SerializedName("locale_names") val localeNames: LocaleNames,
    @SerializedName("_geoloc") val geoLocation: GeoLoc,
)

data class GeoLoc(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lon: Double,
)


data class LocaleNames(
    @SerializedName("default") val default: List<String>,
)



