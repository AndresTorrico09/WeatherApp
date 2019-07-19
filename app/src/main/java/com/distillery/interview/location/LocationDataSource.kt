package com.distillery.interview.location

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import java.util.*

/**
 * Loads last known location.
 */
class LocationDataSource(private val context: Context) {

    private val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)

    fun requestLastKnownLocation(callback: (Location?) -> Unit) {
        // Base for location fetch is implemented, but you need to handle everything else
        fusedLocationProvider.lastLocation
            .addOnCompleteListener { task ->
                val location = task.getLocationOrNull()
                callback(location?.toDomainLocation())
            }
    }

    private fun android.location.Location.getCityName(): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(this.latitude, this.longitude, 3)
        return addresses.find { it.locality != null }?.locality
    }

    private fun android.location.Location.getCountryCode(): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(this.latitude, this.longitude, 3)
        return addresses.find { it.countryCode != null }?.countryCode
    }

    private fun android.location.Location.toDomainLocation(): Location {
        return Location(this.latitude, this.longitude, this.getCityName(), this.getCountryCode())
    }

    private fun Task<android.location.Location>.getLocationOrNull(): android.location.Location? {
        val location = this.result
        val isSuccessful = this.isSuccessful
        return if (isSuccessful && location != null) {
            location
        } else {
            null
        }
    }
}
