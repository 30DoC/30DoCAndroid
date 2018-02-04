package com.palzzak.blur.ui.quiz

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.response.Quiz

/**
 * Created by yooas on 2018-01-07.
 */
interface QuizContract {
    interface View: BaseView<Presenter> {
        fun printTextWithNumber(num: Int)
        fun setQuestions(questions: ArrayList<Quiz>)
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
        fun loadRandomQuizSet(memberId: Long)
    }
}