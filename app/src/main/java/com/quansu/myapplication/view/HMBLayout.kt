package com.quansu.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.quansu.myapplication.R
import kotlin.math.abs
import kotlin.math.max

class HMBLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedLinearLayout(context, attrs, defStyleAttr) {

    private var nestedParentView: ChildNestedScrollView? = null
    private var headHeight: Int = 0
    private var head: View? = null
    private var middle: View? = null
    private var bottom: NestedScrollView? = null
    private var lastY: Float = 0f

    override fun onFinishInflate() {
        super.onFinishInflate()
        nestedParentView = findViewById(R.id.nested_parent_view)
        head = findViewById(R.id.lay_head)
        middle = findViewById(R.id.lay_middle)
        bottom = findViewById(R.id.lay_bottom)
        val content = findViewById<LinearLayout>(R.id.lay_content)

        for (i in 1..100) {
            content.addView(TextView(context).apply {
                text = "hello__$i"
                textSize = 20f
                gravity = Gravity.CENTER
            })
        }

        viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            val globalVisibleRect = head?.getGlobalVisibleRect(rect)
            Log.d(VIEW_LOG_TAG, "addOnGlobalLayoutListener: $globalVisibleRect")
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d(VIEW_LOG_TAG, "onLayout:  l:$l t:$t r:$r b:$b")

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        headHeight = head!!.measuredHeight

    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    @SuppressLint("RestrictedApi")
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
//        super.onNestedPreScroll(target, dx, dy, consumed, type)
        if (dy > 0) {
            val parentOffsetY = nestedParentView?.computeVerticalScrollOffset() ?: 0
            val offsetDelta = headHeight - (parentOffsetY + dy)
            Log.d(VIEW_LOG_TAG, "RECT: height $parentOffsetY dy: $dy offsetDelta: $offsetDelta")
            if (offsetDelta > 0) {
                Log.d(VIEW_LOG_TAG, "1")
            } else {
                bottom?.scrollBy(0, -offsetDelta)
                consumed[1] = -offsetDelta
            }
        }
        if (dy < 0) {

            val maxScrollY = bottom?.computeVerticalScrollOffset() ?: 0
            val offsetDelta = (maxScrollY + dy)
            Log.d(
                VIEW_LOG_TAG,
                "RECT: height $maxScrollY dy: $dy offsetDelta: $offsetDelta canScrollVertically: ${
                    bottom?.canScrollVertically(1)
                }"
            )


            if (maxScrollY > abs(dy)) {
                bottom?.scrollBy(0, dy)
                consumed[1] = dy
            } else {
                bottom?.scrollBy(0, -maxScrollY)
                consumed[1] = -maxScrollY
            }
        }

    }


}
