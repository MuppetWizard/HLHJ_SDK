package com.hjhl.hlhj_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hjhl.animalMatching_SDK.api.ZMKit

class MainActivity : AppCompatActivity() {

    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn)
        onclick()
    }

    private fun onclick() {
        btn.setOnClickListener {
            ZMKit.actionSDK(this)
//            ZMKit.setColor("#FF018786","#FFBB86FC","#FF6200EE")
//            ZMKit.initSDK("123456464")
        }
    }
}