package com.distillery.interview.forecastfivedays

import com.distillery.interview.basemvp.BasePresenter
import com.distillery.interview.basemvp.BaseView
import java.util.*

interface FiveDayForecastContract {
    interface View : BaseView {
        fun setWeatherItems(items: List<WeatherUiItem>)
    }

    interface Presenter : BasePresenter<View>
}

// Could be replaced with any other model if you want to, you can keep that one if you wish
data class WeatherUiItem(
    val date: Date,
    // Temp is in Kelvin, i.e. 286.80 which is equal to 16.8 Celsius
    val temp: Double,
    // Humidity is in percents i.e. 75 equals to 75% Humidity
    val humidity: Double,
    // Pressure is in mmHg, i.e. 972.73 mmHg equal to 32.51 atm or 3294.043 kPa
    val pressure: Double
)
