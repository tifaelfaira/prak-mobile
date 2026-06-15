package com.example.roujwa_apps.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.roujwa_apps.R

object NotificationHelper {

    private const val CHANNEL_ID = "default_channel"

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        intent: Intent
    ) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "General",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val pending = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Ambil Vector Asset ic_notification dan ubah jadi Bitmap dengan aman biar gak crash
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_notification)
        val largeIconBitmap = drawable?.let {
            val bitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.setBounds(0, 0, canvas.width, canvas.height)
            it.draw(canvas)
            bitmap
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Ikon kecil di pojok status bar luar
            .setLargeIcon(largeIconBitmap) // TARUK DI SINI: Biar ikonnya muncul gede di dalam badan pop-up putih!
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pending)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Maksa pop-up langsung nongol di atas layar saat diklik
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}