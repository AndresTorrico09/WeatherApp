package com.distillery.interview.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.source.SearchRepository

class SearchViewModel(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    fun getCities(newText: String?) = liveData {
        emit(Result.Loading())

        runCatching {
            searchRepository.getCities(newText)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Error(it))
        }
    }


    class Factory(
        private val searchRepository: SearchRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(
            modelClass: Class<T>,
        ): T {
            return modelClass.getConstructor(SearchRepository::class.java)
                .newInstance(searchRepository)
        }
    }
}