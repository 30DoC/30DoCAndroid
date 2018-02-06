package com.palzzak.blur.ui.quiz

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Quiz

/**
 * Created by yooas on 2018-02-04.
 */
class QuizAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    var mQuizzes: List<Quiz> = arrayListOf()
    override fun getItem(position: Int): Fragment = QuizFragment.create(mQuizzes[position])
    override fun getCount(): Int = mQuizzes.size
}