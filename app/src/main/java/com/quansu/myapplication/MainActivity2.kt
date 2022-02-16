package com.quansu.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private val logTag: String = "ActivityLifeCycle2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate invoked")
        setContentView(R.layout.activity_main2)
        findViewById<Button>(R.id.btn_change).setOnClickListener {
            finish()
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(logTag, "onSaveInstanceState invoked")
        outState.apply {
            putString("text", findViewById<TextView>(R.id.tv_text).text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(logTag, "onRestoreInstanceState invoked")
        findViewById<TextView>(R.id.tv_text).text = savedInstanceState.getString("text")
    }

}
