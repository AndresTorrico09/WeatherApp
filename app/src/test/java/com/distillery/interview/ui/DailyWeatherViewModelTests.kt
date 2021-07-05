package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.daily_weather.DailyWeatherViewModel
import com.distillery.interview.util.MainCoroutineRule
import com.distillery.interview.util.getOrAwaitValue
import com.distillery.interview.util.provideFakeCoroutinesDispatcherProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DailyWeatherViewModelTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var viewModel: DailyWeatherViewModel

    @Before
    fun setUp() {
        weatherRepository = mock()
        viewModel =
            DailyWeatherViewModel(weatherRepository, provideFakeCoroutinesDispatcherProvider())
    }

    @Test
    fun getDailyWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN: I want to know what is the weather
        val mockDailyWeatherResponse = mock<DailyWeatherResponse>()

        whenever(weatherRepository.getDailyWeather()).thenReturn(
            Result.Success(mockDailyWeatherResponse)
        )

        //WHEN: I open my app or move to weather screen
        viewModel.getDailyWeather()

        //THEN: I should to get weather successfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Success(mockDailyWeatherResponse)
        )
    }

    @Test
    fun getDailyWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN: I want to know what is the weather
        val mockDailyWeatherResponse = listOf("errorResponseMessage")

        whenever(weatherRepository.getDailyWeather()).thenReturn(
            Result.Error(mockDailyWeatherResponse)
        )

        //WHEN: I open my app or move to weather screen
        viewModel.getDailyWeather()

        //THEN: I should to get weather unsuccessfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Error(mockDailyWeatherResponse)
        )

    }
}
