package com.quansu.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.quansu.myapplication.view.HMBLayout

class ViewDrawActivity : AppCompatActivity() {
    private val logTag: String = "ActivityLifeCycle2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate invoked")
        val from = intent?.getIntExtra("from", 0)

        if (from == 0) {
            setContentView(R.layout.activity_view_draw)
        } else if (from == 1) {
            recyclerViewDemo()
        } else if (from == 2) {
            viewPagerDemo()
        }
    }

    private fun recyclerViewDemo() {
        setContentView(R.layout.activity_view_draw_recyclerview)
        val recyclerView = findViewById<RecyclerView>(R.id.lay_bottom)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = object : RecyclerView.Adapter<VhDraw>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): VhDraw {
                val itemView = layoutInflater.inflate(R.layout.item_rv_draw, parent, false)
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

    private fun viewPagerDemo() {
        setContentView(R.layout.activity_view_draw_viewpager)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val hmbLayout = findViewById<HMBLayout>(R.id.lay_root)

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return ItemFragment.newInstance(position + 1,hmbLayout)
            }
        }
    }


}
