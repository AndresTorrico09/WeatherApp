package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.util.MainCoroutineRule
import com.distillery.interview.data.WeatherRepository
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.util.getOrAwaitValue
import com.distillery.interview.util.provideFakeCoroutinesDispatcherProvider
import com.distillery.interview.ui.current_weather.CurrentWeatherViewModel
import com.distillery.interview.util.Event
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setUp() {
        weatherRepository = mock()
        viewModel =
            CurrentWeatherViewModel(weatherRepository, provideFakeCoroutinesDispatcherProvider())
    }

    @Test
    fun getCurrentWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN: I want to know what is the current weather
        val mockWeatherResponse = mock<WeatherResponse>()

        val expected = CurrentWeatherViewModel.CurrentWeatherUiModel(
            showLoading = false,
            showError = null,
            showSuccess = Event(mockWeatherResponse),
        )

        //WHEN: I open my app or move to current weather screen
        whenever(weatherRepository.getCurrentWeather()).thenReturn(
            Result.Success(mockWeatherResponse)
        )
        viewModel.getCurrentWeather()

        //THEN: I should to get current weather successfully
        Assert.assertEquals(viewModel.uiState.getOrAwaitValue(), expected)
    }

    @Test
    fun getCurrentWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN: I want to know what is the current weather
        val mockWeatherResponse = listOf("errorResponseMessage")

        //WHEN: I open my app or move to current weather screen
        whenever(weatherRepository.getCurrentWeather()).thenReturn(
            Result.Error(mockWeatherResponse)
        )
        viewModel.getCurrentWeather()

        //THEN: I should to get current weather unsuccessfully
        val expected = CurrentWeatherViewModel.CurrentWeatherUiModel(
            showLoading = false,
            showError = Event(mockWeatherResponse.first()),
            showSuccess = null,
        )

        Assert.assertEquals(viewModel.uiState.getOrAwaitValue(), expected)

    }
}
