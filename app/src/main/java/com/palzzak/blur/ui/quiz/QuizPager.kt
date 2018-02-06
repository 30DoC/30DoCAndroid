package com.palzzak.blur.ui.quiz

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by jaeyoonyoo on 2018. 2. 6..
 */
class QuizPager(context: Context, attrs: AttributeSet?): ViewPager(context, attrs) {
    constructor(context: Context): this(context, null)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = false
    override fun onTouchEvent(ev: MotionEvent?): Boolean = false
}