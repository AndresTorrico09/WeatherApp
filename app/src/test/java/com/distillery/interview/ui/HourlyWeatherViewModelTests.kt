package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.hourly_weather.HourlyWeatherViewModel
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
class HourlyWeatherViewModelTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var viewModel: HourlyWeatherViewModel

    @Before
    fun setUp() {
        weatherRepository = mock()
        viewModel =
            HourlyWeatherViewModel(weatherRepository, provideFakeCoroutinesDispatcherProvider())
    }

    @Test
    fun getHourlyWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN: I want to know what is the today weather
        val mockHourlyWeatherResponse = mock<HourlyWeatherResponse>()

        whenever(weatherRepository.getHourlyWeather()).thenReturn(
            Result.Success(mockHourlyWeatherResponse)
        )

        //WHEN: I open my app or move to today weather screen
        viewModel.getHourlyWeather()

        //THEN: I should to get today weather successfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Success(mockHourlyWeatherResponse)
        )
    }

    @Test
    fun getHourlyWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN: I want to know what is the today weather
        val mockHourlyWeatherResponse = listOf("errorResponseMessage")

        whenever(weatherRepository.getHourlyWeather()).thenReturn(
            Result.Error(mockHourlyWeatherResponse)
        )

        //WHEN: I open my app or move to today weather screen
        viewModel.getHourlyWeather()

        //THEN: I should to get today weather unsuccessfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Error(mockHourlyWeatherResponse)
        )

    }
}
