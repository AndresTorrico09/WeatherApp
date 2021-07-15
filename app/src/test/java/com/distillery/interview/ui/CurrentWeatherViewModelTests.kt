package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.distillery.interview.util.MainCoroutineRule
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.WeatherResponse
import com.distillery.interview.data.source.WeatherRepository
import com.distillery.interview.ui.current_weather.CurrentWeatherViewModel
import com.distillery.interview.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelTests {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

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
    fun getCurrentWeather_returnSuccessResponse() = testCoroutineDispatcher.runBlockingTest {
        //GIVEN: I want to know what is the current weather
        val mockWeatherResponse = mock<WeatherResponse>()
        whenever(weatherRepository.getCurrentWeather()).thenReturn(
            Result.Success(mockWeatherResponse)
        )
        pauseDispatcher()
        val live = viewModel.getCurrentWeather()
        val loading = live.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)
        resumeDispatcher()
        val success = live.getOrAwaitValue()
        resumeDispatcher()
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun getCurrentWeather_returnErrorResponse() = runBlockingTest {
        whenever(weatherRepository.getCurrentWeather()).thenReturn(
            Result.Error()
        )
        pauseDispatcher()
        val live = viewModel.getCurrentWeather()
        val loading = live.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)
        resumeDispatcher()
        val success = live.getOrAwaitValue()
        resumeDispatcher()
        Assert.assertTrue(success is Result.Error)
    }
}
