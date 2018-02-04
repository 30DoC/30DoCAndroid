package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-07.
 */
@PerActivity
class QuizPresenter @Inject constructor(): QuizContract.Presenter {
    @Inject
    lateinit var mQuizView: QuizContract.View

    @Inject
    lateinit var mAPIService: APIService

    override fun printInitialText() {
        var numberOfQuestions = 10
        //numberOfQuestions = mAPIService.getSetOfQuestions().getInt("questionskey")
        mQuizView.printTextWithNumber(numberOfQuestions)
    }
}
