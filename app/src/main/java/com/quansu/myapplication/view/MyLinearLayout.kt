package com.quansu.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.quansu.myapplication.R

class MyLinearLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedLinearLayout(context, attrs, defStyleAttr) {

    private var headHeight: Int? = null
    private var head: View? = null
    private var middle: View? = null
    private var bottom: View? = null
    private var lastY: Float = 0f

    override fun onFinishInflate() {
        super.onFinishInflate()
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
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val layoutParams = bottom?.layoutParams
        layoutParams?.height = measuredHeight - (middle?.measuredHeight ?: 0)
        bottom?.layoutParams = layoutParams

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                lastY = event.y
//                true
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val curY = event.y
//                val gap = (lastY - curY).toInt()
//                Log.d(VIEW_LOG_TAG, "curY: $curY")
//                Log.d(VIEW_LOG_TAG, "lastY: $lastY")
//                Log.d(VIEW_LOG_TAG, "gap: $gap")
////                if (abs(gap) > ViewConfiguration.getTouchSlop()) {
//                scrollBy(0, gap)
////                }
//                lastY = curY
//                true
//            }
//            else -> {
//                return super.onTouchEvent(event)
//            }
//        }
//
//    }

    override fun scrollTo(x: Int, y: Int) {
        Log.d(VIEW_LOG_TAG, "scrollTo: $y")
        var destY = y
        if (y < 0) {
            destY = 0
        }
        if (y > headHeight ?: 0) {
            destY = headHeight ?: 0
        }
        super.scrollTo(x, destY)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        headHeight = head?.measuredHeight
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
        val hideHead = dy > 0 && scrollY < headHeight ?: 0
        val canScrollVertically = target.canScrollVertically(-1)
        val showHead = dy < 0 && !canScrollVertically
        if (hideHead || showHead) {
            scrollBy(0, dy)
            consumed[1] = dy
        }
    }


    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        Log.d(VIEW_LOG_TAG, "dxConsumed: $dxConsumed")
        Log.d(VIEW_LOG_TAG, "dyConsumed: $dyConsumed")
        Log.d(VIEW_LOG_TAG, "dxUnconsumed: $dxUnconsumed")
        Log.d(VIEW_LOG_TAG, "dyUnconsumed: $dyUnconsumed")
        //当子控件处理完后，交给父控件进行处理。
        if (dyUnconsumed < 0) {
            //表示已经向下滑动到头
            scrollBy(0, dyUnconsumed)
        }
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.d(VIEW_LOG_TAG, "velocityY: $velocityY")

        return false
    }


    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return false
    }

}
