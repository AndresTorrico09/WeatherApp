package com.distillery.interview.ui

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.distillery.interview.R
import com.distillery.interview.databinding.ActivityMainBinding
import com.distillery.interview.notifications.BasicStyleMockData
import com.distillery.interview.notifications.InboxStyleMockData
import com.distillery.interview.notifications.NotificationUtil

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mNotificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)

        binding.btnInboxNotification.setOnClickListener(this)
        binding.btnBasicNotification.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_inbox_notification -> {
                generateInboxStyleNotification()
            }
            R.id.btn_basic_notification -> {
                generateBasicStyleNotification()
            }
        }
    }

    private fun generateInboxStyleNotification() {
        val notificationChannelId =
            NotificationUtil().createInboxStyleNotificationChannel(this)

        val inboxStyle =
            NotificationCompat.InboxStyle()
                .setBigContentTitle(InboxStyleMockData.mBigContentTitle)
                .setSummaryText(InboxStyleMockData.mSummaryText)

        for (summary in InboxStyleMockData.mIndividualEmailSummary()) {
            inboxStyle.addLine(summary)
        }

        val notificationTapAction = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            buildInboxStyleNotification(notificationChannelId, inboxStyle, notificationTapAction)

        mNotificationManagerCompat.notify(INBOX_NOTIFICATION_ID, notification)

    }

    private fun buildInboxStyleNotification(
        notificationChannelId: String,
        inboxStyle: NotificationCompat.InboxStyle?,
        notificationTapAction: PendingIntent?
    ): Notification {
        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext, notificationChannelId
        )

        notificationCompatBuilder.setStyle(inboxStyle)
            .setContentTitle(InboxStyleMockData.mContentTitle)
            .setContentText(InboxStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher_background
                )
            )
            .setContentIntent(notificationTapAction)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            .setSubText(InboxStyleMockData.nNumberOfNewEmails.toString())
            .setCategory(Notification.CATEGORY_EMAIL)
            .setPriority(InboxStyleMockData.mPriority)
            .setVisibility(InboxStyleMockData.mChannelLockscreenVisibility)
            .setAutoCancel(true)

        for (name in InboxStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }

        return notificationCompatBuilder.build()
    }


    private fun generateBasicStyleNotification() {
        val notificationChannelId =
            NotificationUtil().createBasicStyleNotificationChannel(this)

        val notificationTapAction = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = buildBasicStyleNotification(
            notificationChannelId, notificationTapAction
        )

        mNotificationManagerCompat.notify(BASIC_NOTIFICATION_ID, notification)
    }

    private fun buildBasicStyleNotification(
        notificationChannelId: String,
        notificationTapAction: PendingIntent?
    ): Notification {

        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext, notificationChannelId
        )

        notificationCompatBuilder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(BasicStyleMockData.mContentTitle)
            .setContentText(BasicStyleMockData.mContentText)
            .setPriority(BasicStyleMockData.mPriority)
            .setContentIntent(notificationTapAction)
            .setAutoCancel(true)

        return notificationCompatBuilder.build()

    }

    companion object {
        const val INBOX_NOTIFICATION_ID = 888
        const val BASIC_NOTIFICATION_ID = 999
    }
}
