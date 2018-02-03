package com.palzzak.blur.ui.quiz

import android.os.Bundle
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz_desc.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import java.util.*


class QuizActivity : DaggerAppCompatActivity(), QuizContract.View {
    @Inject
    lateinit var mQuizPresenter: QuizPresenter

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

        }, 5000)
    }

    private fun transitToQuizScreen() {
        setContentView(R.layout.activity_quiz)
    }

}
