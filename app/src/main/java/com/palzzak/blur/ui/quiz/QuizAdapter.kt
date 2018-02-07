package com.palzzak.blur.ui.quiz

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by yooas on 2018-02-04.
 */
class QuizAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    var mFragments: List<QuizFragment> = arrayListOf()

    override fun getItem(position: Int): Fragment = mFragments[position]
    override fun getCount(): Int = mFragments.size
}