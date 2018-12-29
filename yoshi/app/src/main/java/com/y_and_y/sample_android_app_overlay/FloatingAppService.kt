package com.y_and_y.sample_android_app_overlay

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.WindowManager
import android.widget.ImageView
import java.util.*
import com.y_and_y.sample_android_app_overlay.FloatingButton

class FloatingAppService : Service() {

    companion object {
        val ACTION_START = "start"
        val ACTION_STOP = "stop"
    }

    private val notificationId = Random().nextInt()

    private var button: FloatingButton? = null

    override fun onCreate() {
        super.onCreate()
        startNotification()
    }

    private fun startNotification() {
        val activityIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)
        val notification = Notification.Builder(this)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(FloatingAppService::class.simpleName)
            .setContentText("Service is running.")
            .build()
        startForeground(notificationId, notification)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null || intent.action == ACTION_START) {
            startOverlay()
        } else {
            stopSelf()
        }
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopOverlay()
    }

    private fun startOverlay() {
        ImageView(this).run {
            val windowManager = getSystemService(Service.WINDOW_SERVICE) as WindowManager
            setImageResource(android.R.drawable.ic_menu_add)
            button = FloatingButton(windowManager, this).apply {
                visible = true
            }
        }
    }

    private fun stopOverlay() {
        button?.run {
            visible = false
        }
        button = null
    }
}