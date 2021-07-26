package com.distillery.interview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.distillery.interview.data.models.LocationPreferences

class MainViewModel : ViewModel() {
    private val _locationLiveData = MutableLiveData<LocationPreferences>()
    val locationLiveData = _locationLiveData

    fun setLocation(lat: Double?, lon: Double?) {
        locationLiveData.value = LocationPreferences(lat, lon)
    }
}

