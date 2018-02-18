package com.palzzak.blur.ui.question

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.response.Question

/**
 * Created by stevehan on 2018. 2. 1..
 */

interface QuestionContract {
    interface View: BasePresenter<View> {
        fun printDescription()
        fun setQuestions(questions: ArrayList<Question>)
    }

    interface Presenter: BaseView<Presenter> {
        fun init(memberId: Long)
    }
}