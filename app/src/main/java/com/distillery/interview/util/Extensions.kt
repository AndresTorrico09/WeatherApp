package com.distillery.interview.util

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDate(): String? = try {
    val sdf = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
    val netDate = Date(this.toLong() * 1000)
    sdf.format(netDate)
} catch (e: Exception) {
    e.toString()
}

fun Int.toTime(): String? = try {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val netDate = Date(this.toLong() * 1000)
    sdf.format(netDate)
} catch (e: Exception) {
    e.toString()
}