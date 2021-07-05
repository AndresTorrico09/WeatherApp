package com.distillery.interview.ui.daily_weather

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.distillery.interview.data.CoroutinesDispatcherProvider
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyWeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<DailyWeatherResponse>>()
    val uiState: LiveData<Result<DailyWeatherResponse>> = _uiState

    fun getDailyWeather() = viewModelScope.launch(coroutinesDispatcherProvider.default) {
        withContext(coroutinesDispatcherProvider.main) {
            _uiState.value = Result.Loading()
        }

        try {
            when (val result = weatherRepository.getDailyWeather()) {
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
            return DailyWeatherViewModel(weatherRepository, coroutinesDispatcherProvider) as T
        }
    }
}

