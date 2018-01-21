package com.paljjak.thirtydoc.activities.quiz

import com.paljjak.thirtydoc.BasePresenter
import com.paljjak.thirtydoc.BaseView

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