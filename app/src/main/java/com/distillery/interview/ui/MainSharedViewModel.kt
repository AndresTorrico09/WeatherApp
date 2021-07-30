package com.distillery.interview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.distillery.interview.data.models.GeoLocation

class MainSharedViewModel : ViewModel() {
    private val _locationLiveData = MutableLiveData<GeoLocation>()
    val locationLiveData = _locationLiveData

    fun setLocation(lat: Double?, lon: Double?) {
        locationLiveData.value = GeoLocation(lat, lon)
    }
}

