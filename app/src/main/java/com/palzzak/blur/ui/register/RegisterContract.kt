package com.palzzak.blur.ui.register

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.response.SimpleQuiz

/**
 * Created by stevehan on 2018. 2. 1..
 */

interface RegisterContract {
    interface View: BasePresenter<View> {
        fun setQuestions(questions: List<SimpleQuiz>)
        fun finishActivity()
    }

    interface Presenter: BaseView<Presenter> {
        fun loadInitialQuestionSet(memberId: Long)
        fun registQuiz(memberId: Long, quizList: List<SimpleQuiz>)
    }
}