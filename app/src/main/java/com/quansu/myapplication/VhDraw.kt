package com.quansu.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VhDraw(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTextView: TextView
        get() {
            return itemView.findViewById<TextView>(R.id.tv_text)
        }
}
