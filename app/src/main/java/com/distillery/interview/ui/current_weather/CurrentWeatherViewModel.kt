package com.distillery.interview.ui.current_weather

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeatherViewModel(
    private val weatherAPI: WeatherAPI
) : ViewModel() {

    private val _uiState = MutableLiveData<CurrentWeatherUiModel>()
    val uiState: LiveData<CurrentWeatherUiModel>
        get() = _uiState

    private fun emitUiModel(
        showLoading: Boolean = false,
        showError: Event<String>? = null,
        showSuccess: Event<WeatherResponse?>? = null
    ) {
        val uiModel = CurrentWeatherUiModel(showLoading, showError, showSuccess)
        _uiState.value = uiModel
    }

    data class CurrentWeatherUiModel(
        val showLoading: Boolean,
        val showError: Event<String>?,
        val showSuccess: Event<WeatherResponse?>?
    )

    fun getCurrentWeather() = viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.Main) { showLoading() }

        val result = weatherAPI.getCurrentWeather()
        when {
            result.isSuccessful -> {
                withContext(Dispatchers.Main) {
                    emitUiModel(showSuccess = Event(result.body()))
                }
            }
            else -> {
                withContext(Dispatchers.Main) {
                    emitUiModel(showError = Event(result.message()))
                }
            }
        }
    }

    private fun showLoading() {
        emitUiModel(showLoading = true)
    }

    class Factory(
        owner: SavedStateRegistryOwner,
        defaultState: Bundle?,
        private val weatherAPI: WeatherAPI
    ) : AbstractSavedStateViewModelFactory(owner, defaultState) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return CurrentWeatherViewModel(weatherAPI) as T
        }
    }
}

