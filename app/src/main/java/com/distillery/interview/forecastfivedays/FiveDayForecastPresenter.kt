package com.distillery.interview.forecastfivedays

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.distillery.interview.forecastfivedays.FiveDayForecastContract.Presenter
import com.distillery.interview.forecastfivedays.FiveDayForecastContract.View
import com.distillery.interview.jsonutils.JsonFromFileLoader
import org.json.JSONObject
import java.util.Date
import kotlin.collections.ArrayList

private const val mockJsonFileName = "weather_openweathermap_example_week.json"

class FiveDayForecastPresenter(
    private val jsonFromFileLoader: JsonFromFileLoader
) : Presenter {

    override lateinit var view: View

    override fun onStart(view: View) {
        super.onStart(view)
        this.view = view
        // TODO implement your own loading method and use real API in a way that is convenient for you
        //   you can use any network library and architecture approach in order to do so
        uglyLoadItemsMethod {
            view.setWeatherItems(it)
        }
    }

    /**
     * Method is for demonstration purpose only.
     * In case you want to start from UI and you need some mock to show.
     */
    private fun uglyLoadItemsMethod(uglyCallback: (List<WeatherUiItem>) -> Unit) {
        val uiHandler = Handler(Looper.getMainLooper())
        val handlerThread = HandlerThread("UglyHandlerThread")
        handlerThread.start()
        val looper = handlerThread.looper
        val handler = Handler(looper)

        handler.post {
            val json = jsonFromFileLoader.loadJson(mockJsonFileName)
            checkNotNull(json)
            val items = uglyMapper(json)

            uiHandler.post {
                uglyCallback(items)
            }
        }
    }

    /**
     * Method is for demonstration purpose only.
     * In case you want to start from UI and you need some mock to show.
     */
    private fun uglyMapper(json: JSONObject): List<WeatherUiItem> {
        val resultingList = ArrayList<WeatherUiItem>()
        val list = json.getJSONArray("list")
        for (i in 0 until list.length()) {
            val item = list.getJSONObject(i)
            val main = item.getJSONObject("main")
            val temp = main.getDouble("temp")
            val humidity = main.getDouble("humidity")
            val pressure = main.getDouble("pressure")
            val timestamp = item.getLong("dt")
            resultingList += WeatherUiItem(Date(timestamp), temp, humidity, pressure)
        }
        return resultingList
    }
}
