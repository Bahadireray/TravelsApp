package com.example.bahadir_eray_bootcampfinishproject.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.util.Constants.Companion.CHANNEL_ID
import com.example.bahadir_eray_bootcampfinishproject.util.Constants.Companion.CHANNEL_NAME
import com.example.bahadir_eray_bootcampfinishproject.util.Constants.Companion.DESCRIPTION
import com.example.bahadir_eray_bootcampfinishproject.util.Constants.Companion.NOTIFICATION_ID
import com.example.bahadir_eray_bootcampfinishproject.view.activity.MainActivity

class Notification(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {


    override fun doWork(): Result {
        Log.d(
            "success Work", "doWork: Success function called"
        )
        showNotification()
        return Result.success()
    }


    private fun showNotification() {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        val notification = NotificationCompat.Builder(
            applicationContext, CHANNEL_ID.toString()
        ).setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(CHANNEL_NAME)
            .setContentText(DESCRIPTION)
            .setPriority(NotificationCompat.PRIORITY_MAX).setAutoCancel(true)
            .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID.toString(), CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, notification.build())
        }

    }


}
