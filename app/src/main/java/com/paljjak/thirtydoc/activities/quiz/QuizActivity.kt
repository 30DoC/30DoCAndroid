package com.paljjak.thirtydoc.activities.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.LinearLayoutManager
import com.paljjak.thirtydoc.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz_desc.*
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class QuizActivity : DaggerAppCompatActivity(), QuizContract.View {
    @Inject
    lateinit var mQuizPresenter: QuizPresenter

    val mAdapter: QuizAdapter = QuizAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desc)

        mQuizPresenter.printInitialText()
    }

    override fun printDescAndTransit(numberOfQuestions: Int) {
        id_desc.text = String.format(getString(R.string.quiz_description), numberOfQuestions)
        startQuizAfterSeconds()
    }

    private fun startQuizAfterSeconds() {
        object: CountDownTimer(5000, 1000) {
            var count = 5
            override fun onFinish() {
                transitToQuizScreen()
            }

            override fun onTick(p0: Long) {
                id_countdown.text = count.toString()
                count--
            }

        }.start()
    }

    private fun transitToQuizScreen() {
        setContentView(R.layout.activity_quiz)
        id_quiz_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        id_quiz_recycler.adapter = mAdapter
    }
}
