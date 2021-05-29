package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.WeatherRepository
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.ui.today_weather.TodayWeatherViewModel
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
class TodayWeatherViewModelTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var viewModel: TodayWeatherViewModel

    @Before
    fun setUp() {
        weatherRepository = mock()
        viewModel =
            TodayWeatherViewModel(weatherRepository, provideFakeCoroutinesDispatcherProvider())
    }

    @Test
    fun getTodayHourlyWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN: I want to know what is the today weather
        val mockHourlyWeatherResponse = mock<HourlyWeatherResponse>()

        whenever(weatherRepository.getHourlyWeather()).thenReturn(
            Result.Success(mockHourlyWeatherResponse)
        )

        //WHEN: I open my app or move to today weather screen
        viewModel.getTodayHourlyWeather()

        //THEN: I should to get today weather successfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Success(mockHourlyWeatherResponse)
        )
    }

    @Test
    fun getTodayHourlyWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN: I want to know what is the today weather
        val mockHourlyWeatherResponse = listOf("errorResponseMessage")

        whenever(weatherRepository.getHourlyWeather()).thenReturn(
            Result.Error(mockHourlyWeatherResponse)
        )

        //WHEN: I open my app or move to today weather screen
        viewModel.getTodayHourlyWeather()

        //THEN: I should to get today weather unsuccessfully
        Assert.assertEquals(
            viewModel.uiState.getOrAwaitValue(),
            Result.Error(mockHourlyWeatherResponse)
        )

    }
}
