package com.quansu.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewDrawActivity : AppCompatActivity() {
    private val logTag: String = "ActivityLifeCycle2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate invoked")
//        setContentView(R.layout.activity_view_draw)
        setContentView(R.layout.activity_view_draw_recyclerview)

        val recyclerView = findViewById<RecyclerView>(R.id.lay_bottom)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = object : RecyclerView.Adapter<VhDraw>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): VhDraw {
                val itemView = layoutInflater.inflate(R.layout.item_rv_draw, parent,false)
                return VhDraw(itemView)
            }

            @SuppressLint("SetTextI18n")
            override fun onBindViewHolder(holder: VhDraw, position: Int) {
                holder.tvTextView.text = "HelloWorld $position"

            }

            override fun getItemCount(): Int {
                return 50
            }


        }

    }

    inner class VhDraw(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTextView: TextView
            get() {
                return itemView.findViewById<TextView>(R.id.tv_text)
            }
    }

}
