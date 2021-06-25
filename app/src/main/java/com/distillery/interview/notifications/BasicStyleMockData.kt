package com.distillery.interview.notifications

import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

object BasicStyleMockData {
    const val mChannelId = "channel_basic_1"
    const val mChannelName = "Sample Basic"
    const val mChannelDescription = "Sample Basic Notifications"

    @RequiresApi(Build.VERSION_CODES.N)
    const val mChannelImportance = NotificationManager.IMPORTANCE_DEFAULT
    const val mChannelEnableVibrate = true
    const val mChannelLockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE

    const val mContentTitle = "Basic Title"
    const val mContentText = "basic content notification"
    const val mPriority = NotificationCompat.PRIORITY_DEFAULT
}