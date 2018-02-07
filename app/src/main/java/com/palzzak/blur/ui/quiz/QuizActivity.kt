package com.palzzak.blur.ui.quiz

import android.animation.ObjectAnimator
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Quiz
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz_desc.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import java.util.*
import kotlin.collections.ArrayList
import android.view.animation.DecelerateInterpolator
import com.palzzak.blur.util.AlertDialogFactory


class QuizActivity : DaggerAppCompatActivity(), QuizContract.View, View.OnClickListener {
    @Inject
    lateinit var mQuizPresenter: QuizPresenter

    @Inject
    lateinit var mSharedPrefs: SharedPreferences

    private val mAdapter = object: FragmentPagerAdapter(supportFragmentManager) {
        var mFragments: List<QuizFragment> = arrayListOf()
        override fun getItem(position: Int): Fragment = mFragments[position]
        override fun getCount(): Int = mFragments.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desc)

        mQuizPresenter.init(mSharedPrefs.getLong(Constants.PREF_MEMBER_ID_KEY, -1L))
        startQuizAfterSeconds()
    }

    override fun printTextWithNumber(num: Int) {
        id_desc.text = String.format(getString(R.string.quiz_description), num)
    }

    private fun startQuizAfterSeconds() {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                launch(UI) {
                    transitToQuizScreen()
                }
            }

        }, 3000)
    }

    private fun transitToQuizScreen() {
        setContentView(R.layout.activity_quiz)
        id_quiz_pager.adapter = mAdapter
        id_quiz_pager.pageMargin
        id_prev_button.setOnClickListener(this)
        id_answer_false_button.setOnClickListener(this)
        id_answer_true_button.setOnClickListener(this)

        mQuizPresenter.loadQuiz()
        updatePageProgress(1)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.id_prev_button -> {
                if (id_quiz_pager.currentItem == 0) {
                    AlertDialogFactory.show(fragmentManager, Constants.DIALOG_TAG_QUIT)
                } else {
                    id_quiz_pager.currentItem = id_quiz_pager.currentItem - 1
                }
            }
            else -> {
                mQuizPresenter.setQuizAnswer(id_quiz_pager.currentItem, v.id == R.id.id_answer_true_button)
                id_quiz_pager.currentItem = id_quiz_pager.currentItem + 1
            }
        }
        updatePageProgress(id_quiz_pager.currentItem + 1)
        if (id_quiz_pager.currentItem + 1 == id_quiz_pager.adapter.count) {
            // TODO("GO TO RESULT SCREEN")
        }
    }

    override fun setQuestions(questions: ArrayList<Quiz>) {
        mAdapter.mFragments = QuizFragment.createFragments(questions)
        mAdapter.notifyDataSetChanged()
    }

    private fun updatePageProgress(page: Int) {
        id_page_number_text.text = "${page}/${id_quiz_pager.adapter.count}"
        val progress = (page.toDouble() / mAdapter.count * 100).toInt()
        ObjectAnimator.ofInt(id_page_progress, "progress", progress).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
        }.start()
    }
}