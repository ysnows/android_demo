package com.quansu.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val logTag: String = "ActivityLifeCycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate invoked")
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_change).setOnClickListener {
            findViewById<TextView>(R.id.tv_text).text = "hello activity"
        }
        findViewById<Button>(R.id.btn_jump).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        findViewById<Button>(R.id.btn_jump_view_draw).setOnClickListener {
            startActivity(Intent(this, ViewDrawActivity::class.java))
        }
        findViewById<Button>(R.id.btn_jump_view_draw3).setOnClickListener {
            startActivity(Intent(this, ViewDrawActivity::class.java).apply {
                putExtra("from", 2)
            })
        }
        findViewById<Button>(R.id.btn_jump_view_draw2).setOnClickListener {
            startActivity(Intent(this, ViewDrawActivity::class.java).apply {
                putExtra("from", 1)
            })
        }
        findViewById<Button>(R.id.btn_jump_view_scroller).setOnClickListener {
            startActivity(Intent(this, ScrollerActivity::class.java))
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
