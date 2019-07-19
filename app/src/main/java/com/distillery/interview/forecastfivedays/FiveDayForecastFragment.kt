package com.distillery.interview.forecastfivedays

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distillery.interview.R
import com.distillery.interview.jsonutils.JsonFromFileLoader

class FiveDayForecastFragment : Fragment(), FiveDayForecastContract.View {

    // You can use DI framework to inject presenter
    private lateinit var presenter: FiveDayForecastContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast_fivedays, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FiveDayForecastPresenter(JsonFromFileLoader(context!!))
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
    }

    @UiThread
    override fun setWeatherItems(items: List<WeatherUiItem>) {
        // TODO implement adapter and set items to the recycler view
        Log.d(this.javaClass.simpleName, "Items that are set to view: $items")
    }
}
