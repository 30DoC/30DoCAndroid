package com.palzzak.blur.ui.quiz

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.response.QuizSet

/**
 * Created by yooas on 2018-01-07.
 */
interface QuizContract {
    interface View: BaseView<Presenter> {
        fun printTextWithNumber(num: Int)
        fun setQuestions(questions: QuizSet)
        fun showResultScreen(result: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun init(memberId: Long)
        fun setQuizAnswer(index: Int, answer: Boolean)
        fun loadQuiz()
        fun submitMyAnswers()
    }
}