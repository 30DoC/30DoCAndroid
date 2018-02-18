package com.palzzak.blur.ui.question

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.APIService
import javax.inject.Inject

/**
 * Created by stevehan on 2018. 2. 1..
 */
@PerActivity
class QuestionPresenter @Inject constructor(): QuestionContract.Presenter {
    @Inject
    lateinit var mQuestionView: QuestionContract.View

    @Inject
    lateinit var mAPIService: APIService



    override fun init(memberId: Long) {
        //mQuestions = mAPIService.getQuestions(memberId)
        mQuestionView.printDescription()
    }


}