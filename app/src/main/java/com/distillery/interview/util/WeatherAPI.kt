package com.distillery.interview.util

private const val WEATHER_FIVE_DAYS_FORECAST_URL = "api.openweathermap.org/data/2.5/forecast"
private const val WEATHER_TODAY_FORECAST_URL = "api.openweathermap.org/data/2.5/weather"
// Can be copied wherever required.
private const val WEATHER_API_KEY = "140dfec020b9a101a62d3b48acf050e0"

/**
 * Examples of URLs for different requests. You can implement your own api for the selected network solution.
 * See {@link https://openweathermap.org/current} and {@link https://openweathermap.org/forecast5} for details.
 */
class WeatherAPI {

    fun getApiKey(): String {
        return WEATHER_API_KEY
    }

    fun getUrlForFiveDaysForecast(cityName: String, countryCode: String? = null): String {
        return if (countryCode != null) {
            "$WEATHER_FIVE_DAYS_FORECAST_URL?q=$cityName,$countryCode"
        } else {
            "$WEATHER_FIVE_DAYS_FORECAST_URL?q=$cityName"
        }
    }

    fun getUrlForFiveDaysForecast(latitude: Double, longitude: Double): String {
        return "$WEATHER_FIVE_DAYS_FORECAST_URL?lat=$latitude&lon=$longitude"
    }

    fun getUrlForTodayForecast(cityName: String, countryCode: String? = null): String {
        return if (countryCode != null) {
            "$WEATHER_TODAY_FORECAST_URL?q=$cityName,$countryCode"
        } else {
            "$WEATHER_TODAY_FORECAST_URL?q=$cityName"
        }
    }

    fun getUrlForTodayForecast(latitude: Double, longitude: Double): String {
        return "$WEATHER_TODAY_FORECAST_URL?lat=$latitude&lon=$longitude"
    }
}
