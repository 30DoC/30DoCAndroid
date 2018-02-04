package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.pojo.Quiz
import java.util.*
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

    override fun loadRandomQuizSet(memberId: Long) {
        //val questions = mAPIService.getQuestions(memberId)
        val questions = arrayListOf(
                Quiz(true, "너는 사람입니까?", 0, Date(), Date()),
                Quiz(true, "여자입니까?", 0, Date(), Date()),
                Quiz(true, "뭘봐요?", 0, Date(), Date()),
                Quiz(true, "키득키득?", 0, Date(), Date())
        )
        mQuizView.setQuestions(questions)
    }
}
