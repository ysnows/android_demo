package com.quansu.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.quansu.myapplication.view.Case1ViewGroup

class ScrollerActivity : AppCompatActivity() {
    private val logTag: String = "ScrollerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller)
        val containerViewGroup: Case1ViewGroup =
            findViewById(R.id.container_viewgroup)
        val leftButton = findViewById<Button>(R.id.button_left)
        val rightButton = findViewById<Button>(R.id.button_right)
        val onButtonClickListener =
            View.OnClickListener { v ->
                val currentIndex: Int = containerViewGroup.getCurrentIndex()
                var targetIndex = currentIndex
                when (v.id) {
                    R.id.button_left -> targetIndex = currentIndex - 1
                    R.id.button_right -> targetIndex = currentIndex + 1
                }
                containerViewGroup.moveToIndex(targetIndex)
            }
        leftButton.setOnClickListener(onButtonClickListener)
        rightButton.setOnClickListener(onButtonClickListener)
    }

}
