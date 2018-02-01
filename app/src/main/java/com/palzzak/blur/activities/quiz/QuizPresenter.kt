package com.palzzak.blur.activities.quiz

import com.palzzak.blur.network.NetworkService
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
    lateinit var mNetworkService: NetworkService

    override fun printInitialText() {
        var numberOfQuestions = 10
        //numberOfQuestions = mNetworkService.getSetOfQuestions().getInt("questionskey")
        mQuizView.printTextWithNumber(numberOfQuestions)
    }
}
