package com.distillery.interview.ui.current_weather

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.CoroutinesDispatcherProvider
import com.distillery.interview.data.WeatherRepository
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<WeatherResponse>>()
    val uiState: LiveData<Result<WeatherResponse>> = _uiState

    fun getCurrentWeather() = viewModelScope.launch(coroutinesDispatcherProvider.default) {
        withContext(coroutinesDispatcherProvider.main) {
            _uiState.value = Result.Loading()
        }

        try {
            when (val result = weatherRepository.getCurrentWeather()) {
                is Result.Success -> {
                    withContext(coroutinesDispatcherProvider.main) {
                        _uiState.value = Result.Success(result.data)
                    }
                }
                is Result.Error -> {
                    withContext(coroutinesDispatcherProvider.main) {
                        _uiState.value = Result.Error(result.errors)
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.value = Result.Error(listOf(e.message.toString()))
        }
    }

    class Factory(
        owner: SavedStateRegistryOwner,
        private val weatherRepository: WeatherRepository,
        private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ) : AbstractSavedStateViewModelFactory(owner, null) {

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

