package com.distillery.interview.ui.current_weather

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.api.WeatherAPI
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeatherViewModel(
    private val weatherAPI: WeatherAPI
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<WeatherResponse>>()
    val uiState: LiveData<Result<WeatherResponse>>
        get() = _uiState

    fun getCurrentWeather() = viewModelScope.launch(Dispatchers.Default) {
        withContext(Dispatchers.Main) {
            _uiState.value = Result.Loading()
        }

        //TODO: Replace with call from Repository
        try {
            val response = weatherAPI.getCurrentWeather()
            when {
                response.isSuccessful -> {
                    withContext(Dispatchers.Main) {
                        _uiState.value = Result.Success(response.body()!!)
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        _uiState.value = Result.Error(listOf(response.message()))
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.value = Result.Error(listOf(e.message.toString()))
        }
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

