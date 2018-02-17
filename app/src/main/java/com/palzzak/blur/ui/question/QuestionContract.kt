package com.palzzak.blur.ui.question

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.response.SimpleQuiz

/**
 * Created by stevehan on 2018. 2. 1..
 */

interface QuestionContract {
    interface View: BasePresenter<View> {
        fun setQuestions(questions: List<SimpleQuiz>)
    }

    interface Presenter: BaseView<Presenter> {
        fun loadInitialQuestionSet(memberId: Long)
    }
}