package com.loyer.recyclerview_example.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
* Created by loyer on 29.03.2018.
*/

class NoSwipePager(context: Context, attrs: AttributeSet): ViewPager(context,attrs) {

    override fun onTouchEvent(ev: MotionEvent?) = false

    override fun onInterceptTouchEvent(ev: MotionEvent?) = false
}