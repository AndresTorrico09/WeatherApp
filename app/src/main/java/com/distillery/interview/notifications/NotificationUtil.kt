package com.distillery.interview.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationUtil {

    fun createInboxStyleNotificationChannel(context: Context): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = InboxStyleMockData.mChannelId
            val channelName = InboxStyleMockData.mChannelName
            val channelDescription = InboxStyleMockData.mChannelDescription
            val channelImportance = InboxStyleMockData.mChannelImportance
            val channelEnableVibrate = InboxStyleMockData.mChannelEnableVibrate
            val channelLockscreeVisibility = InboxStyleMockData.mChannelLockscreenVisibility

            //Initializes Notification Channel
            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
            notificationChannel.description = channelDescription
            notificationChannel.enableVibration(channelEnableVibrate)
            notificationChannel.lockscreenVisibility = channelLockscreeVisibility

            //Adds NotificationChannel to the system
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            return channelId
        }

        return ""
    }

    fun createBasicStyleNotificationChannel(context: Context): String {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = BasicStyleMockData.mChannelId
            val channelName = BasicStyleMockData.mChannelName
            val channelDescription = BasicStyleMockData.mChannelDescription
            val channelImportance = BasicStyleMockData.mChannelImportance
            val channelEnableVibrate = BasicStyleMockData.mChannelEnableVibrate
            val channelLockscreeVisibility = BasicStyleMockData.mChannelLockscreenVisibility

            //Initializes Notification Channel
            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
            notificationChannel.description = channelDescription
            notificationChannel.enableVibration(channelEnableVibrate)
            notificationChannel.lockscreenVisibility = channelLockscreeVisibility

            //Adds NotificationChannel to the system
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            return channelId
        }

        return ""
    }
}