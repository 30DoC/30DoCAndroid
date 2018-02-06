package com.palzzak.blur.ui.quiz

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
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


class QuizActivity : DaggerAppCompatActivity(), QuizContract.View, View.OnClickListener {
    @Inject
    lateinit var mQuizPresenter: QuizPresenter

    @Inject
    lateinit var mSharedPrefs: SharedPreferences

    private val mAdapter: QuizAdapter = QuizAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desc)

        mQuizPresenter.printInitialText()
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
        id_answer_false_button.setOnClickListener(this)
        id_answer_true_button.setOnClickListener(this)

        val memberId = mSharedPrefs.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mQuizPresenter.loadRandomQuizSet(memberId)
    }

    override fun onClick(v: View) {
        var answer: Boolean
        when (v.id) {
            R.id.id_answer_false_button -> answer = false
            R.id.id_answer_true_button -> answer = true
        }
        id_quiz_pager.currentItem = id_quiz_pager.currentItem + 1
    }

    override fun setQuestions(questions: ArrayList<Quiz>) {
        mAdapter.mQuizzes = questions
        mAdapter.notifyDataSetChanged()
        id_quiz_pager.adapter = mAdapter
    }
}