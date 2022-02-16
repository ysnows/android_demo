package com.quansu.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ViewDrawActivity : AppCompatActivity() {
    private val logTag: String = "ActivityLifeCycle2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate invoked")
        setContentView(R.layout.activity_view_draw)
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart invoked")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume invoked")
    }

    override fun onPause() {
        super.onPause()
        Log.d(logTag, "onPause invoked")
    }

    override fun onStop() {
        super.onStop()
        Log.d(logTag, "onStop invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "onDestroy invoked")
    }
}
