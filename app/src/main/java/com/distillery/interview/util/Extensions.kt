package com.distillery.interview.util

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDateTime(): String? = try {
    val sdf = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
    val netDate = Date(this.toLong() * 1000)
    sdf.format(netDate)
} catch (e: Exception) {
    e.toString()
}