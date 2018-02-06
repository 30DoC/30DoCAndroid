package com.palzzak.blur.ui.question

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.response.Question
import java.util.*
import javax.inject.Inject

/**
 * Created by stevehan on 2018. 2. 1..
 */
@PerActivity
class QuestionPresenter @Inject constructor(): QuestionContract.Presenter {
    @Inject
    lateinit var mQuestionView: QuestionContract.View


    override fun printQuestionDescription() {
        var numberOfQuestions = 10
        mQuestionView.printEditText(numberOfQuestions)
    }

    override fun loadInitialQuestionSet(memberId: Long) {

        val questions = arrayListOf<Question>()

        for (i in 1..10) {
            questions.add(Question(true, "", i, Date(), Date()))
        }

        mQuestionView.setQuestions(questions)
    }
}