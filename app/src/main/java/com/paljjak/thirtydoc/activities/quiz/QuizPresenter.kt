package com.paljjak.thirtydoc.activities.quiz

import com.paljjak.thirtydoc.data.network.NetworkService
import com.paljjak.thirtydoc.di.PerActivity
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
        mQuizView.printDescriptionWithNumber(numberOfQuestions)
    }
}
