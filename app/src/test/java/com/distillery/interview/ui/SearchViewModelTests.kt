package com.distillery.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.SearchBodyRequest
import com.distillery.interview.data.models.SearchResponse
import com.distillery.interview.data.source.SearchRepository
import com.distillery.interview.ui.search.SearchViewModel
import com.distillery.interview.util.MainCoroutineRule
import com.distillery.interview.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        searchRepository = mock()
        searchViewModel = SearchViewModel(searchRepository)
    }

    @Test
    fun testGetCities_withSearchText_returnSuccess() = runBlockingTest {
        //given
        val mockSearchResponse = mock<SearchResponse>()
        val searchText = "paris"
        val mockRequest = SearchBodyRequest(searchText)

        whenever(searchRepository.getCities(mockRequest)).thenReturn(mockSearchResponse)

        //when
        val liveDataResponse = searchViewModel.getCities(searchText)

        //then
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val success = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(success is Result.Success)
    }

    @Test
    fun testGetCities_withSearchText_returnError() = runBlockingTest {
        //given
        val mockError = RuntimeException()
        val searchText = "paris"
        val mockRequest = SearchBodyRequest(searchText)

        whenever(searchRepository.getCities(mockRequest)).thenThrow(mockError)

        //when
        val liveDataResponse = searchViewModel.getCities(searchText)

        //then
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        val error = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(error is Result.Error)
    }

    @Test
    fun testGetCities_withEmptyText_returnEmptyListSuccess() = runBlockingTest {
        //given
        val mockSearchResponse = SearchResponse(arrayListOf())
        val searchText = ""

        //when
        val liveDataResponse = searchViewModel.getCities(searchText)

        //then
        val loading = liveDataResponse.getOrAwaitValue()
        Assert.assertTrue(loading is Result.Loading)

        assertThat(
            liveDataResponse.getOrAwaitValue(),
            equalTo(Result.Success(mockSearchResponse))
        )
    }
}