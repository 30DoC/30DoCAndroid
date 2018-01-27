package com.palzzak.blur.activities.quiz

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView

/**
 * Created by yooas on 2018-01-07.
 */
interface QuizContract {
    interface View: BaseView<Presenter> {
        fun printDescAndTransit(numberOfQuestions: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
    }
}