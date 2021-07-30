package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.GeoLocation
import com.distillery.interview.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainSharedViewModelTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainSharedViewModel: MainSharedViewModel

    @Before
    fun setUp() {
        mainSharedViewModel = MainSharedViewModel()
    }

    @Test
    fun testSetLocation_returnSameValue() {
        //given
        val mockLocation = GeoLocation(0.0, 0.0)

        //when
        mainSharedViewModel.setLocation(mockLocation.lat, mockLocation.lon)

        //then
        assertThat(
            mainSharedViewModel.locationLiveData.getOrAwaitValue(),
            equalTo(mockLocation)
        )
    }
}