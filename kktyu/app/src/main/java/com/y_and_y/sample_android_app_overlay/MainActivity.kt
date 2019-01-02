package com.y_and_y.sample_android_app_overlay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    private var animation: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.raw.lrt).into(image_view)

//        image_view.setBackgroundResource(R.drawable.animation_list01)
//
//        animation = image_view.background as AnimationDrawable
//
//        val button = findViewById<View>(R.id.button)
//        button.setOnClickListener {
//            if (animation!!.isRunning) {
//                // 一度stopしないと再スタートしない
//                animation!!.stop()
//            }
//            animation!!.start()
//        }
    }
}
