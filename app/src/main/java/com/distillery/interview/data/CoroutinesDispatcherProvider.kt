package com.distillery.interview.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provide coroutines context.
 */
data class CoroutinesDispatcherProvider(
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val io: CoroutineDispatcher
) {
    constructor() : this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}