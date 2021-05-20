package com.distillery.interview.util

import com.distillery.interview.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
fun provideFakeCoroutinesDispatcherProvider(
    main: CoroutineDispatcher? = null,
    computation: CoroutineDispatcher? = null,
    io: CoroutineDispatcher? = null
): CoroutinesDispatcherProvider {
    val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
    return CoroutinesDispatcherProvider(
        main ?: sharedTestCoroutineDispatcher,
        computation ?: sharedTestCoroutineDispatcher,
        io ?: sharedTestCoroutineDispatcher
    )
}