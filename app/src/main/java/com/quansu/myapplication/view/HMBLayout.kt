package com.quansu.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ScrollingView
import androidx.core.view.ViewCompat
import kotlin.math.abs

class HMBLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedLinearLayout(context, attrs, defStyleAttr) {

    private var nestedParentView: ChildNestedScrollView? = null
    private var headHeight: Int = 0
    private var head: View? = null
    private var middle: View? = null
    private var bottom: ViewGroup? = null
    private var bottomCurScrollingView: ScrollingView? = null
    private var lastY: Float = 0f

    override fun onFinishInflate() {
        super.onFinishInflate()
        nestedParentView = getChildAt(0) as ChildNestedScrollView?
        head = (nestedParentView?.getChildAt(0) as ViewGroup).getChildAt(0)
        middle = (nestedParentView?.getChildAt(0) as ViewGroup).getChildAt(1)
        bottom = (nestedParentView?.getChildAt(0) as ViewGroup).getChildAt(2) as ViewGroup?

        if (bottom is ScrollingView) {
            setBottomCurScrollingView(bottom as ScrollingView)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d(VIEW_LOG_TAG, "onLayout:  l:$l t:$t r:$r b:$b")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val layoutParams = bottom?.layoutParams
        layoutParams?.height = measuredHeight - middle!!.measuredHeight
        bottom?.layoutParams = layoutParams
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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
            val parentOffsetY = (target as ScrollingView).computeVerticalScrollOffset() ?: 0
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

            val maxScrollY = bottomCurScrollingView?.computeVerticalScrollOffset() ?: 0
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


    fun setBottomCurScrollingView(scrollingView: ScrollingView) {
        bottomCurScrollingView = scrollingView
    }


}
