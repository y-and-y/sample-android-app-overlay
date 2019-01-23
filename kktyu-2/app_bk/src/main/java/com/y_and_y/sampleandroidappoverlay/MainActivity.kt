package com.y_and_y.sampleandroidappoverlay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.MotionEvent
import com.y_and_y.sampleandroidappoverlay.FloatingAppService
import com.y_and_y.sampleandroidappoverlay.hasOverlayPermission
import com.y_and_y.sampleandroidappoverlay.requestOverlayPermission

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.qualifiedName
        private const val REQUEST_OVERLAY_PERMISSION = 1
    }

    private var enabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if (hasOverlayPermission()) {
            val intent = Intent(this, FloatingAppService::class.java)
                    .setAction(FloatingAppService.ACTION_STOP)
            startService(intent)
        } else {
            requestOverlayPermission(REQUEST_OVERLAY_PERMISSION)
        }
    }

    override fun onStop() {
        super.onStop()
        if (enabled && hasOverlayPermission()) {
            val intent = Intent(this, FloatingAppService::class.java)
                    .setAction(FloatingAppService.ACTION_START)
            startService(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_OVERLAY_PERMISSION -> Log.d(TAG, "enable overlay permission")
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        enabled = false
        return super.onTouchEvent(event)
    }
}