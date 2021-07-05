package com.distillery.interview.ui.hourly_weather

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.CoroutinesDispatcherProvider
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HourlyWeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<HourlyWeatherResponse>>()
    val uiState: LiveData<Result<HourlyWeatherResponse>> = _uiState

    fun getHourlyWeather() = viewModelScope.launch(coroutinesDispatcherProvider.default) {
        withContext(coroutinesDispatcherProvider.main) {
            _uiState.value = Result.Loading()
        }

        try {
            when (val result = weatherRepository.getHourlyWeather()) {
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
            withContext(coroutinesDispatcherProvider.main) {
                _uiState.value = Result.Error(listOf(e.message.toString()))
            }
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
            return HourlyWeatherViewModel(weatherRepository, coroutinesDispatcherProvider) as T
        }
    }
}

