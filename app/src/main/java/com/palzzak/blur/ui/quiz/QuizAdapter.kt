package com.palzzak.blur.ui.quiz

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.palzzak.blur.network.response.Quiz

/**
 * Created by yooas on 2018-02-04.
 */
class QuizAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    var mQuizzes: List<Quiz> = arrayListOf()
    override fun getItem(position: Int): Fragment = QuizFragment.create(mQuizzes[position])
    override fun getCount(): Int = mQuizzes.size
}