package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.HourlyWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.hourly_weather.HourlyWeatherViewModel
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
            HourlyWeatherViewModel(weatherRepository)
    }

    @Test
    fun getHourlyWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN
        val mockWeatherResponse = mock<HourlyWeatherResponse>()
        val mockSuccess = Result.Success(mockWeatherResponse)
        whenever(weatherRepository.getHourlyWeather()).thenReturn(mockSuccess)

        //WHEN
        val liveDataResponse = viewModel.getHourlyWeather()

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val success = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun getHourlyWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN
        val mockError = Result.Error()
        whenever(weatherRepository.getHourlyWeather()).thenReturn(mockError)

        //WHEN
        val liveDataResponse = viewModel.getHourlyWeather()

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val error = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(error is Result.Error)
    }
}
