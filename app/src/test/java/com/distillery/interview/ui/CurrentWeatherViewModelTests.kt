package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.CurrentWeatherResponse
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.current_weather.CurrentWeatherViewModel
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
            CurrentWeatherViewModel(weatherRepository)
    }

    @Test
    fun getCurrentWeather_returnSuccessResponse() = runBlockingTest {
        //GIVEN
        val mockWeatherResponse = mock<CurrentWeatherResponse>()
        val lat = 0.0
        val lon = 0.0
        whenever(weatherRepository.getCurrentWeather(lat, lon)).thenReturn(mockWeatherResponse)

        //WHEN
        val liveDataResponse = viewModel.getCurrentWeather(lat, lon)

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val success = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun getCurrentWeather_returnErrorResponse() = runBlockingTest {
        //GIVEN
        val mockError = RuntimeException()
        val lat = 0.0
        val lon = 0.0
        whenever(weatherRepository.getCurrentWeather(lat, lon)).thenThrow(mockError)

        //WHEN
        val liveDataResponse = viewModel.getCurrentWeather(lat, lon)

        //THEN
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val error = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(error is Result.Error)
    }
}
