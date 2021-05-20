package com.distillery.interview.ui.current_weather

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.CoroutinesDispatcherProvider
import com.distillery.interview.data.WeatherRepository
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.util.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
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

    fun getCurrentWeather() = viewModelScope.launch(coroutinesDispatcherProvider.default) {
        withContext(coroutinesDispatcherProvider.main) { showLoading() }

        when (val result = weatherRepository.getCurrentWeather()) {
            is Result.Success -> {
                withContext(coroutinesDispatcherProvider.main) {
                    emitUiModel(showSuccess = Event(result.data))
                }
            }
            is Result.Error -> {
                withContext(coroutinesDispatcherProvider.main) {
                    emitUiModel(showError = Event(result.errors.first()))
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
        private val weatherRepository: WeatherRepository,
        private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ) : AbstractSavedStateViewModelFactory(owner, defaultState) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return CurrentWeatherViewModel(weatherRepository, coroutinesDispatcherProvider) as T
        }
    }
}

