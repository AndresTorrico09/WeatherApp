package com.distillery.interview.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val hits: List<HitsItem?>? = null,
)

data class HitsItem(
    @SerializedName("locale_names") val localeNames: LocaleNames,
)

data class LocaleNames(
    @SerializedName("default") val default: List<String>,
)



