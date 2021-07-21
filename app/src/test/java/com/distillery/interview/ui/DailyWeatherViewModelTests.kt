package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.DailyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.daily_weather.DailyWeatherViewModel
import com.distillery.interview.util.MainCoroutineRule
import com.distillery.interview.util.getOrAwaitValue
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
            DailyWeatherViewModel(weatherRepository)
    }

    @Test
    fun getDailyWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN
        val mockWeatherResponse = mock<DailyWeatherResponse>()
        whenever(weatherRepository.getDailyWeather()).thenReturn(mockWeatherResponse)

        //WHEN
        val liveDataResponse = viewModel.getDailyWeather()

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val success = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun getDailyWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN
        val mockError = RuntimeException()
        whenever(weatherRepository.getDailyWeather()).thenThrow(mockError)

        //WHEN
        val liveDataResponse = viewModel.getDailyWeather()

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val error = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(error is Result.Error)
    }
}
